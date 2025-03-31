package com.axonivy.connectivity.rest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * Tests the REST interface of the {@link com.axonivy.connectivity.rest.provider.SecureService}.
 */
public class IntegrationTestSecureService {
  @Test
  public void invokePermitAllAnonymous() {
    Response response = createClient()
        .target(adminServiceUri).request().get();
    assertThat(response.getStatus()).isEqualTo(Status.OK.getStatusCode());
  }

  @Test
  public void invokeDefaultBasicAuthMethod() {
    Response noAuthResponse = createClient()
        .target(adminServiceUri).request()
        .header("X-Requested-By", "ivy")
        .put(Entity.entity("my new entry", MediaType.TEXT_PLAIN));
    assertThat(noAuthResponse.getStatus()).isEqualTo(Status.UNAUTHORIZED.getStatusCode());

    Response response = createAuthenticatedClient()
        .target(adminServiceUri).request()
        .header("X-Requested-By", "ivy")
        .put(Entity.entity("my new entry", MediaType.TEXT_PLAIN));
    assertThat(response.getStatus()).isEqualTo(Status.OK.getStatusCode());
  }

  @Test
  public void invokeAdminRoleMethod() {
    Response noAuthResponse = createClient()
        .target(adminServiceUri).path("/0").request()
        .header("X-Requested-By", "ivy")
        .post(Entity.entity("my new entry", MediaType.TEXT_PLAIN));
    assertThat(noAuthResponse.getStatus()).isEqualTo(Status.UNAUTHORIZED.getStatusCode());

    Response response = createAuthenticatedClient()
        .target(adminServiceUri).path("/0").request()
        .header("X-Requested-By", "ivy")
        .post(Entity.entity("my new entry", MediaType.TEXT_PLAIN));
    assertThat(response.getStatus()).isEqualTo(Status.FORBIDDEN.getStatusCode());

    Response wrongRoleResponse = createClient()
        .register(HttpAuthenticationFeature.basic("theBoss", "theBoss"))
        .target(adminServiceUri).path("/0").request()
        .header("X-Requested-By", "ivy")
        .post(Entity.entity("my new entry", MediaType.TEXT_PLAIN));
    assertThat(wrongRoleResponse.getStatus()).isEqualTo(Status.OK.getStatusCode());

  }

  @Test
  public void invokeDenyAllMethod() {
    Response response = createAuthenticatedClient()
        .target(adminServiceUri + "/0").request()
        .header("X-Requested-By", "ivy")
        .delete();
    assertThat(response.getStatus()).isEqualTo(Status.FORBIDDEN.getStatusCode());
  }

  public IntegrationTestSecureService() {
    this.adminServiceUri = EngineUrl.createRestUrl("/admin");
  }

  public static final String REST_USER = "restUser";
  private final String adminServiceUri;

  private static Client createAuthenticatedClient() {
    Client httpClient = createClient();
    httpClient.register(HttpAuthenticationFeature.basic(REST_USER, REST_USER));
    return httpClient;
  }

  @SuppressWarnings("deprecation")
  private static Client createClient() {
    Client httpClient = ClientBuilder.newClient();
    httpClient.register(JacksonJsonProvider.class);
    httpClient.register(new org.glassfish.jersey.filter.LoggingFilter());
    return httpClient;
  }

}
