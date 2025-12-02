package com.axonivy.connectivity.rest.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.transport.commons_text.StringEscapeUtils;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Demonstrates a service which protects it's methods with
 * <code>javax.security</code> annotations.
 *
 * <p>
 * By default an ivy REST service requires clients to be authenticated with
 * username and password via HTTP-BASIC. This behaviour can be adjusted with the
 * following annotations:
 * </p>
 * <ul>
 * <li>{@link javax.annotation.security.PermitAll}: allows unauthenticated
 * access to anonymous users</li>
 * <li>{@link javax.annotation.security.RolesAllowed}: users must be
 * authenticated and own the defined roles</li>
 * <li>{@link javax.annotation.security.DenyAll}: nobody is allowed to invoke
 * this service</li>
 * </ul>
 *
 * All these annotations can be set either on the service class or on a specific
 * method. Service class annotations are considered for all methods. But an
 * annotation on a method always override the service class annotation.
 *
 * @since 6.4.0
 */
@Singleton
@Path("admin")
@Tag(name = ApiConstants.DEMO_TAG)
public class SecureService {

  private final List<String> entries = new ArrayList<>(Arrays.asList("Hello world"));

  /**
   * {@link PermitAll}: no authentication required to call this method.
   * Anonymous access granted.
   */
  @GET
  @PermitAll
  @Produces(MediaType.APPLICATION_JSON)
  public Response showEntries() {
    return Response.status(Status.OK)
        .entity(entries)
        .build();
  }

  /**
   * No <code>javax.security</code> annotation present: Defaults to HTTP-BASIC
   * auth required.
   */
  @PUT
  @Consumes(MediaType.TEXT_PLAIN)
  public Response addEntry(String newEntry) {
    var entry = StringEscapeUtils.escapeHtml4(newEntry);
    entries.add(entry);
    return Response.status(Status.OK)
        .entity("Added entry '" + entry + "'")
        .build();
  }

  /**
   * {@link RolesAllowed}: only HTTP-BASIC authenticated users which own the
   * role 'Boss' are allowed to call this method.
   */
  @POST
  @Path("/{entryId}")
  @RolesAllowed("Boss")
  public Response updateEntry(@PathParam("entryId") int id, String newEntry) {
    var entry = StringEscapeUtils.escapeHtml4(newEntry);
    entries.set(id, entry);
    return Response.status(Status.OK)
        .entity("Update entry with id (" + id + ") to '" + entry + "'")
        .build();
  }

  /**
   * {@link DenyAll}: Access to this method is blocked for all users
   */
  @DELETE
  @Path("/{entryId}")
  @DenyAll
  public void removeEntry(@PathParam("entryId") int id) {
    entries.remove(id);
  }

}
