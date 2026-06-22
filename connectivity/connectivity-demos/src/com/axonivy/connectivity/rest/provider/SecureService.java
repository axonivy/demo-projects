package com.axonivy.connectivity.rest.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

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
 * <li>{@link jakarta.annotation.security.PermitAll}: allows unauthenticated
 * access to anonymous users</li>
 * <li>{@link jakarta.annotation.security.RolesAllowed}: users must be
 * authenticated and own the defined roles</li>
 * <li>{@link jakarta.annotation.security.DenyAll}: nobody is allowed to invoke
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
