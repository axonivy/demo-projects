package com.axonivy.connectivity.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpStatus;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.jupiter.api.Test;

import com.axonivy.connectivity.Person;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Tests the REST interface of the {@link com.axonivy.connectivity.rest.provider.PersonService}.
 */
public class IntegrationTestRestfulPersonService {
  public static final String REST_USER = "restUser";
  private static final String UUID_PATTERN = "([a-f0-9]{8}(-[a-f0-9]{4}){3}-[a-f0-9]{11})";

  @Test
  public void getListOfEntities() {
    Response response = getPersonsClient().request().get();
    List<Person> persons = response.readEntity(new GenericType<ArrayList<Person>>(){});
    assertThat(persons).isNotEmpty();
  }

  @Test
  public void filterListOfEntities() {
    Response response = getPersonsClient().queryParam("name", "Weiss").request().get();
    List<Person> persons = response.readEntity(new GenericType<ArrayList<Person>>(){});
    assertThat(persons).hasSize(1);
  }

  @Test
  public void putNewEntity() {
    Entity<Form> entity = createFormPerson();
    Response response = getPersonsClient().request().header("X-Requested-By", "ivy").put(entity);
    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_CREATED);
    assertThat(response.getLink("createdPerson")).isNotNull();
    JsonNode node = response.readEntity(JsonNode.class);
    assertThat(node.get("id").asText()).containsPattern(UUID_PATTERN);
  }

  private Entity<Form> createFormPerson() {
    MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();
    formData.add("firstname", "Junit");
    formData.add("lastname", "Test");
    return Entity.form(formData);
  }

  @Test
  public void updateEntity() {
    Response response = getPersonsClient().request().header("X-Requested-By", "ivy").put(createFormPerson());
    JsonNode node = response.readEntity(JsonNode.class);
    String id = node.get("id").asText();

    Person updatePerson = new Person();
    updatePerson.setId(UUID.fromString(id));
    updatePerson.setFirstname("Junit");
    updatePerson.setLastname("Test");
    Entity<Person> entity = Entity.json(updatePerson);

    response = getPersonsClient().path(updatePerson.getId().toString())
        .request()
        .header("X-Requested-By", "ivy")
        .post(entity);
    assertThat(response.getStatus()).isEqualTo(Status.OK.getStatusCode());
  }

  @Test
  public void deleteEntity() {
    Response response = getPersonsClient().request().header("X-Requested-By", "ivy").put(createFormPerson());
    JsonNode node = response.readEntity(JsonNode.class);
    String id = node.get("id").asText();

    response = getPersonsClient().path(id)
        .request()
        .header("X-Requested-By", "ivy")
        .delete();
    assertThat(response.readEntity(Person.class)).isNotNull();
  }

  private static WebTarget getPersonsClient() {
    return createAuthenticatedClient().target(EngineUrl.createRestUrl("/persons"));
  }

  @SuppressWarnings("deprecation")
  private static Client createAuthenticatedClient() {
    Client httpClient = ClientBuilder.newClient();
    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(REST_USER, REST_USER);
    httpClient.register(com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
    httpClient.register(feature);
    httpClient.register(new org.glassfish.jersey.filter.LoggingFilter());
    return httpClient;
  }

}
