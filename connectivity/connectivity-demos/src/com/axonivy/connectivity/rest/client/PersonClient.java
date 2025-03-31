package com.axonivy.connectivity.rest.client;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.axonivy.connectivity.Person;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.rest.client.GenericTypes;

/**
 * Shows how pre-configured REST Services can be called by using the
 * {@link Ivy#rest} API.
 *
 * @since 6.1.1
 */
public class PersonClient {

  /**
   * GET request that returns a list of complex types.
   */
  public static List<Person> getPersons() {
    return Ivy.rest().client("personService")
        .request().get(GenericTypes.listOf(Person.class));
  }

  /**
   * PUT request the accepts form parameters as input.
   */
  public static Response add(String firstName, String lastName) {
    MultivaluedMap<String, String> formData = new MultivaluedHashMap<>();
    formData.add("firstname", firstName);
    formData.add("lastname", lastName);
    return Ivy.rest().client("personService")
        .request().header("X-Requested-By", "ivy").put(Entity.form(formData));
  }

  /**
   * POST request that sends a complex type instance
   */
  public static Response update(Person person) {
    return Ivy.rest().client("personService")
        .path(person.getId().toString())
        .request().header("X-Requested-By", "ivy").post(Entity.json(person));
  }

  /**
   * DELETE request: defines the person to delete with its path
   */
  public static Response delete(UUID personId) {
    return Ivy.rest().client("personService")
        .path(personId.toString())
        .request().header("X-Requested-By", "ivy").delete();
  }

}
