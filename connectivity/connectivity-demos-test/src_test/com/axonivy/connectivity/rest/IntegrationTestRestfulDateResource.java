package com.axonivy.connectivity.rest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Tests the REST interface of the
 * {@link com.axonivy.connectivity.rest.provider.DateResource}.
 */
class IntegrationTestRestfulDateResource {

  public static final String REST_USER = "restUser";

  @Test
  void customizedJsonDates() {
    Response response = getPersonsClient().request().get();
    JsonNode json = response.readEntity(JsonNode.class);
    String localizedDate = json.get("delivery").asText();
    assertThat(localizedDate)
        .as("dates are customized by the @Provider deployed with the project")
        .endsWith("Z");
  }

  private static WebTarget getPersonsClient() {
    String uri = EngineUrl.createRestUrl("/dates");
    return createAuthenticatedClient().target(uri);
  }

  private static Client createAuthenticatedClient() {
    Client httpClient = ClientBuilder.newClient();
    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(REST_USER, REST_USER);
    httpClient.register(com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
    httpClient.register(feature);
    return httpClient;
  }
}
