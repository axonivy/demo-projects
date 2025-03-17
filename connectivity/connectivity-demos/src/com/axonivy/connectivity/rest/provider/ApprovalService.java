package com.axonivy.connectivity.rest.provider;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.task.responsible.Responsible;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * RESTful Service that demonstrates how to interact with the ivy environment
 * such as processes.
 *
 * <p>
 * A sample request can be fired with the process start:
 * <b>approval/createApprovalViaREST.ivp</b>
 * </p>
 */
@Path("approve")
@Tag(name = ApiConstants.DEMO_TAG)
public class ApprovalService {

  /**
   * Runs a callable SubProcess to create new Task for a boss.
   *
   * @param title
   * @param description
   * @return created approval task
   */
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response create(
      @FormParam("title") String title,
      @FormParam("description") String description) {
    ITask task = SubProcessCall.withPath("rest/createApproval")
        .withParam("title", title)
        .withParam("description", description)
        .call()
        .get("approvalTask", ITask.class);

    var uri = "/api/workflow/task/{id}";
    var createdLink = Link.fromPath(uri).rel("approvalTask").build(task.getId());
    return Response.status(Status.CREATED)
        .location(UriBuilder.fromPath(uri).build(task.getId()))
        .links(createdLink)
        .entity(new TaskMeta(task))
        .build();
  }

  public static class TaskMeta {
    public final long id;
    public final String name;
    public final String description;
    public final String activator;

    public TaskMeta(ITask task) {
      this.id = task.getId();
      this.name = task.getName();
      this.description = task.getDescription();
      this.activator = task.responsibles().all().stream()
          .map(Responsible::get)
          .map(ISecurityMember::getMemberName)
          .findFirst()
          .orElse(null);
    }
  }
}
