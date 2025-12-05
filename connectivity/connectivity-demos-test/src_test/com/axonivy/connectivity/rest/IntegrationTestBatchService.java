package com.axonivy.connectivity.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * Tests the REST interface of the {@link com.axonivy.connectivity.rest.provider.BatchService}.
 */
public class IntegrationTestBatchService {
  public static final String REST_USER = "restUser";

  @Test
  public void async() throws Exception {
    WebTarget target = createAuthenticatedClient()
        .target(EngineUrl.createRestUrl("/batch/async"))
        .queryParam("blockSeconds", 1);
    Future<Response> future = target.request().async().get();
    Response asyncResponse = future.get(10, TimeUnit.SECONDS);

    assertThat(asyncResponse.readEntity(String.class))
        .isEqualTo("Sorry for the slow processing!");
    assertThat(asyncResponse.getHeaderString("responseThread"))
        .as("annotated with @ManagedAsync: should not run in tomcat thread pool.")
        .startsWith("jersey-server-managed-async-executor-");
    assertThat(asyncResponse.getHeaderString("sessionUser"))
        .as("ivy context must be super save restored also in non tomcat thread pools.")
        .isEqualTo(REST_USER);
  }

  private static Client createAuthenticatedClient() {
    Client httpClient = ClientBuilder.newClient();
    httpClient.register(JacksonJsonProvider.class);
    httpClient.register(HttpAuthenticationFeature.basic(REST_USER, REST_USER));
    return httpClient;
  }

}
