package com.axonivy.connectivity.rest.provider;

import ch.ivyteam.ivy.engine.rest.service.annotation.csrf.DisableCsrfProtection;
import ch.ivyteam.ivy.environment.Ivy;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Simulates a service that allows modifying request (POST, PUT, DELETE) without the CSRF protection header (X-Requested-By).
 * This allows you to fulfil a strict API contract for a third-party that can't send custom headers.
 * 
 * <p>Alternatively the protection can be globally disabled for all service, 
 * by setting <code>REST.Servlet.CSRF.Protection=false</code> on the Engine.</p>
 */
@Path("chat")
@DisableCsrfProtection // opt-out CSRF protection.
@Tag(name = ApiConstants.DEMO_TAG)
public class ChatService {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response executeBlocking(Message message) {
    return Response.status(200)
        .entity("received message for "+message.to())
        .header("responseThread", Thread.currentThread().getName())
        .header("sessionUser", Ivy.session().getSessionUserName())
        .build();
  }

  public static record Message(String message, String to) {

  }

}
