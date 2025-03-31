package com.axonivy.connectivity.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.awaitility.Awaitility;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.client.filter.CsrfProtectionFilter;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;

import com.axonivy.connectivity.rest.aynch.chat.ChatMessage;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * Tests the REST interface of the {@link com.axonivy.connectivity.rest.provider.SecureService}.
 */
public class IntegrationTestChatService {

  @Test
  public void p2p_onlineChat() throws Exception {
    Future<Response> cliFuture = createAuthenticatedClient().target(chatResource).request().async().get();

    Response sentResp = postSync(chatResource + "/" + REST_USER, REST_USER, "hey, how are you?");
    assertThat(sentResp.getStatusInfo().getFamily()).isEqualTo(Family.SUCCESSFUL);

    Response cliResponse = cliFuture.get(2, TimeUnit.SECONDS);
    assertThat(cliResponse.getStatusInfo().getFamily()).isEqualTo(Family.SUCCESSFUL);

    List<ChatMessage> messages = cliResponse.readEntity(MSG_LIST);
    assertThat(messages.get(0).message).isEqualTo("hey, how are you?");
  }

  @Test
  public void p2p_offlineChat() throws Exception {
    Response sentResp = postSync(chatResource + "/" + REST_USER, REST_USER, "can you send me the files?");
    assertThat(sentResp.getStatusInfo().getFamily()).isEqualTo(Family.SUCCESSFUL);

    Response cliResponse = createAuthenticatedClient()
        .target(chatResource).request()
        .async().get().get(2, TimeUnit.SECONDS);
    assertThat(cliResponse.getStatusInfo().getFamily()).isEqualTo(Family.SUCCESSFUL);

    List<ChatMessage> messages = cliResponse.readEntity(MSG_LIST);
    assertThat(messages.get(0).message).isEqualTo("can you send me the files?");
  }

  private Response postSync(String resource, String as, String message) {
    return createClient().register(HttpAuthenticationFeature.basic(as, as))
        .target(resource).request()
        .header(CsrfProtectionFilter.HEADER_NAME, "ivy")
        .post(Entity.entity(message, MediaType.TEXT_PLAIN));
  }

  @Test
  public void highLoad() throws Throwable {
    int clientCount = 50;// 2000; // feel free to push limits harder - but for
                         // CI we relax.

    System.out.print("Creating " + clientCount + " named users...");
    createTestUsers(clientCount);
    System.out.println(" DONE!");

    List<AsyncChatClient> clients = createConcurrentClients(clientCount);
    ExecutorService pool = Executors.newFixedThreadPool(30);
    clients.forEach(callback -> pool.submit(callback.toCall(chatResource)));

    System.out.print("Waiting for " + clientCount + " listening clients...");
    Awaitility.await().timeout(30, TimeUnit.SECONDS)
        .pollInterval(2, TimeUnit.SECONDS)
        .until(() -> countConnectedClients().equals(clientCount));
    System.out.println(" DONE!");

    Response sentResp = postSync(chatResource, "theBoss", "hello world :-)");
    assertThat(sentResp.getStatusInfo().getFamily()).isEqualTo(Family.SUCCESSFUL);

    System.out.print("Closing client pool ...");
    pool.shutdown();
    pool.awaitTermination(30, TimeUnit.SECONDS);
    System.out.println(" DONE!");

    System.out.print("Testing client results ...");
    testClients(clients);
    System.out.println(" DONE!");
  }

  private void testClients(List<AsyncChatClient> clients) throws Throwable {
    for (int i = 0; i < clients.size(); i++) {
      if (i % 100 == 0) {
        System.out.print(i);
      }
      assertSuccess(clients.get(i));
    }
  }

  private void createTestUsers(int clientCount) throws IOException, ClientProtocolException {
    String createUsers = "1675F33D16FB90A4";
    String url = EngineUrl.createProcessUrl("/connectivity-demos-test/" + createUsers +
        "/createTestUsers.ivp?prefix=test&amount=" + clientCount);
    CloseableHttpClient client = HttpClients.createDefault();
    client.execute(new HttpGet(url));
  }

  private Integer countConnectedClients() {
    Integer cliCount = silentClient().target(chatResource + "/count").request().get()
        .readEntity(Integer.class);
    System.out.print("..." + cliCount);
    return cliCount;
  }

  private void assertSuccess(AsyncChatClient client) throws Throwable {
    Throwable error = client.error.get();
    if (error != null) {
      throw error;
    }

    Awaitility.await().timeout(2, TimeUnit.SECONDS)
        .pollInterval(2, TimeUnit.MILLISECONDS)
        .untilAtomic(client.messages, IsNull.notNullValue());

    List<ChatMessage> msgs = client.messages.get();
    assertThat(msgs).hasSize(1);
    assertThat(msgs.get(0).message).endsWith("hello world :-)");
  }

  private List<AsyncChatClient> createConcurrentClients(int amount) {
    List<AsyncChatClient> clients = new ArrayList<>();
    for (int i = 0; i < amount; i++) {
      clients.add(new AsyncChatClient("test" + i));
    }
    return clients;
  }

  private final String chatResource = EngineUrl.createRestUrl("/chatdemo");

  public static final String REST_USER = "restUser";

  private static final GenericType<List<ChatMessage>> MSG_LIST = new GenericType<>(){};

  private static Client createAuthenticatedClient() {
    return createClient().register(HttpAuthenticationFeature.basic(REST_USER, REST_USER));
  }

  @SuppressWarnings("deprecation")
  private static Client createClient() {
    return silentClient().register(new org.glassfish.jersey.filter.LoggingFilter());
  }

  private static Client silentClient() {
    return ClientBuilder.newClient().register(JacksonJsonProvider.class);
  }

  private static class AsyncChatClient implements InvocationCallback<List<ChatMessage>> {
    public final AtomicReference<List<ChatMessage>> messages = new AtomicReference<>(null);
    public final AtomicReference<Throwable> error = new AtomicReference<>(null);
    public final String user;

    public AsyncChatClient(String user) {
      this.user = user;
    }

    @Override
    public void completed(List<ChatMessage> response) {
      messages.set(response);
    }

    @Override
    public void failed(Throwable throwable) {
      error.set(throwable);
    }

    public Callable<Void> toCall(String resource) {
      return () -> {
        Client client = silentClient().register(HttpAuthenticationFeature.basic(user, user));
        client.target(resource).request().async().get(AsyncChatClient.this);
        return null;
      };
    }
  }

}
