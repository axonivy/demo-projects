package com.axonivy.connectivity.rest.aynch.chat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import com.axonivy.connectivity.rest.provider.ApiConstants;

import io.swagger.v3.oas.annotations.tags.Tag;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Demonstrates asynchronous REST communication:
 *
 * <ul>
 * <li>Users that join the chat fire an asynchronous (@GET) request and wait for
 * new messages.</li>
 * <li>New messages are sent synchronous (@Post) and will be distributed to
 * asynchronous listeners.</li>
 * </ul>
 *
 * @author rew
 * @since 7.3.0
 */
@Singleton
@Path("chatdemo")
@Tag(name = ApiConstants.DEMO_TAG)
public class ChatService {

  private final Map<String, AsyncResponse> responses = new ConcurrentHashMap<>();
  private final Map<String, List<ChatMessage>> offlineMessages = new HashMap<>();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized void read(@Suspended AsyncResponse response) {
    String listener = Ivy.session().getSessionUserName();
    if (offlineMessages.containsKey(listener)) {
      List<ChatMessage> messages = offlineMessages.remove(listener);
      Ivy.log().debug("sending messages collected as user was offline: " + messages);
      response.resume(messages);
      return;
    }
    responses.put(listener, response);
  }

  @POST
  @Path("/{receiverName}")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.APPLICATION_JSON)
  public synchronized ChatMessage writePrivate(String messageText, @PathParam("receiverName") String receiver) {
    String sender = Ivy.session().getSessionUserName();
    ChatMessage message = new ChatMessage(sender, receiver, messageText);

    AsyncResponse onlineReceiver = responses.remove(receiver);
    if (onlineReceiver != null && onlineReceiver.isSuspended()) {
      Ivy.log().debug("send online:" + message);
      onlineReceiver.resume(Arrays.asList(message));
    } else {
      Ivy.log().debug("store offline:" + message);
      offlineMessages.putIfAbsent(receiver, new LinkedList<>());
      offlineMessages.get(receiver).add(message);
    }
    return message;
  }

  @POST
  @Consumes("text/plain")
  @RolesAllowed("Boss")
  public synchronized void writePublic(String messageText) {
    String sender = Ivy.session().getSessionUserName();
    ChatMessage message = new ChatMessage(sender, "ALL", messageText);
    Ivy.log().debug("send to (" + responses.size() + ") clients: " + message);

    Collection<AsyncResponse> clients = new ArrayList<>(responses.values());
    for (AsyncResponse response : clients) {
      response.resume(Arrays.asList(message));
    }
  }

  @GET
  @Path("/users")
  @Produces(MediaType.APPLICATION_JSON)
  public List<String> onlineUsers() {
    String myself = Ivy.session().getSessionUserName();
    return responses.keySet().stream()
        .filter(user -> !myself.equals(user))
        .collect(Collectors.toList());
  }

  @GET
  @Path("/count")
  @PermitAll
  public int countClients() {
    return responses.size();
  }

}
