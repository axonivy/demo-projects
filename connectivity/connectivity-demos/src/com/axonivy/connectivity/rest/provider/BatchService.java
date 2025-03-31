package com.axonivy.connectivity.rest.provider;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ManagedAsync;

import ch.ivyteam.ivy.environment.Ivy;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Simulates a slow remote service that takes seconds to deliver a result.
 *
 * <p>
 * See <b>Processes/rest/batchService</b> for usage samples
 * </p>
 */
@Path("batch")
@Tag(name = ApiConstants.DEMO_TAG)
public class BatchService {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String executeBlocking(@QueryParam("blockSeconds") Integer secondsToBlock)
      throws InterruptedException {
    simulateLongRunningExecution(secondsToBlock);
    return "Sorry for the delay!";
  }

  /**
   * Non blocking batch processing. <br/>
   * Clients can continue immediately and react on the response when it is
   * available.
   *
   * @param secondsToBlock simulates slow processing time
   * @param asyncResponse asynchronous client request
   * @since 7.3.0
   */
  @GET
  @Path("/async")
  @Produces(MediaType.TEXT_PLAIN)
  @ManagedAsync // auto executes method body in a separate thread pool!
  public void executeAsync(
      @Suspended AsyncResponse asyncResponse,
      @QueryParam("blockSeconds") Integer secondsToBlock) {
    try {
      simulateLongRunningExecution(secondsToBlock);

      Response result = Response.status(200)
          .entity("Sorry for the slow processing!")
          .header("responseThread", Thread.currentThread().getName())
          .header("sessionUser", Ivy.session().getSessionUserName())
          .build();

      asyncResponse.resume(result);
    } catch (InterruptedException ex) {
      asyncResponse.resume(ex); // propagate exception async
    }
  }

  private void simulateLongRunningExecution(Integer secondsToBlock) throws InterruptedException {
    if (secondsToBlock == null) {
      secondsToBlock = 32;
    }
    Thread.sleep(TimeUnit.SECONDS.toMillis(secondsToBlock));
  }

}
