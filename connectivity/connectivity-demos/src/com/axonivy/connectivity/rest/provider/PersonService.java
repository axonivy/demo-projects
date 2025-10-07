package com.axonivy.connectivity.rest.provider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Singleton;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.connectivity.Person;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Simple RESTful service. The REST interface is defined by the JAX-RS
 * annotations on the methods and its path.<br/>
 *
 * <p>
 * <b>URL</b><br/>
 * The simplest external URL of this service will be: <br/>
 * - designer: <code>http://localhost:8081/designer/api/persons</code><br/>
 * - engine:
 * <code>http://localhost:8081/myApplicationName/api/persons</code>
 * </p>
 *
 * <p>
 * <b>Authentication</b><br/>
 * - Consumers of this service must be authenticated with HTTP-BASIC. In the
 * Designer any 'Test User' of the application is valid.
 * </p>
 *
 * @since 6.1.1
 */
@Singleton
@Path("persons")
@Tag(name = ApiConstants.DEMO_TAG)
public class PersonService
{

  private Map<UUID, Person> persons = new HashMap<UUID, Person>();

  public PersonService()
  {
    addNewPerson("Bruno", "Bütler");
    addNewPerson("Reto", "Weiss");
    addNewPerson("Renato", "Stalder");
    addNewPerson("Reguel", "Wermelinger");
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(description = "lists persons which are well known in the ivy-core society.")
  public List<Person> getPersons(
    @QueryParam("name") 
    @Parameter(description = "filters persons which contain the given name part in first or lastname.") 
    String name)
  {
    if (StringUtils.isBlank(name))
    {
      return new ArrayList<>(persons.values());
    }
    return findPersons(name);
  }

  private List<Person> findPersons(String name)
  {
    List<Person> matches = new ArrayList<>();
    for (Person candidate : persons.values())
    {
      if (candidate.getFirstname().contains(name) ||
              candidate.getLastname().contains(name))
      {
        matches.add(candidate);
      }
    }
    return matches;
  }

  @GET
  @Path("/{entryNo}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response getPerson(@PathParam("entryNo") int entryNo)
  {
    try
    {
      UUID personId = new ArrayList<>(persons.keySet()).get(entryNo);
      return Response.status(Status.OK)
              .entity(persons.get(personId))
              .build();
    }
    catch (IndexOutOfBoundsException ex)
    {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response add(
    @Parameter(required = true) @FormParam("firstname") @Size(min = 2, max = 255) String firstname,
    @Parameter(required = true) @FormParam("lastname") String lastname)
  {
    Person person = addNewPerson(firstname, lastname);
    Link createdLink = Link.fromPath("persons/{id}").rel("createdPerson").build(person.getId());
    return Response.status(Status.CREATED)
            .location(createdLink.getUri())
            .links(createdLink)
            .entity(new CreatedPersonMeta(person.getId()))
            .build();
  }

  public static class CreatedPersonMeta
  {
    public final UUID id;
    public final Calendar createdAt;

    private CreatedPersonMeta(UUID uuid)
    {
      this.id = uuid;
      this.createdAt = Calendar.getInstance();
    }
  }

  @DELETE
  @Path("/{personId}")
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(description = "remove a person from the ivy-core universe.")
  public Response deletePerson(@PathParam("personId") UUID personId)
  {
    try
    {
      Person deleted = persons.remove(personId);
      return Response.status(Status.OK)
              .entity(deleted)
              .build();
    }
    catch (IndexOutOfBoundsException ex)
    {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @POST
  @Path("/{personId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("personId") UUID personId, Person person)
  {
    try
    {
      Person existing = persons.get(personId);
      persons.replace(existing.getId(), person);
      return Response.status(Status.OK)
              .build();
    }
    catch (IndexOutOfBoundsException ex)
    {
      return Response.status(Status.NOT_FOUND)
              .entity("user with given personId does not exist.")
              .build();
    }
  }

  private Person addNewPerson(String firstname, String lastname)
  {
    Person person = new Person();
    person.setFirstname(firstname);
    person.setLastname(lastname);
    person.setId(UUID.randomUUID());
    persons.put(person.getId(), person);
    return person;
  }
}
