package com.axonivy.connectivity.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.environment.IvyTest;
import jakarta.ws.rs.client.Entity;

/**
 * Tests the REST interface of the {@link com.axonivy.connectivity.rest.provider.ChatService}.
 */
@IvyTest(enableWebServer = true)
public class TestChatCsrfFree {
  public static final String REST_USER = "restUser";

  @Test
  void noCsrf() throws Exception {
    var target = Ivy.rest().client("ivyengine-localbackend").path("chat");
    var message = new com.axonivy.connectivity.rest.provider.ChatService.Message("hello", "user2000");
    var response = target.request().post(Entity.json(message));
    var responseBody = response.readEntity(String.class);
    System.out.println("Response: " + responseBody);
    assertThat(response.getStatus()).isEqualTo(200);
  }

}
