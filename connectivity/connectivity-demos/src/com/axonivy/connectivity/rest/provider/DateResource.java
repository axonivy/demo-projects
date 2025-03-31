package com.axonivy.connectivity.rest.provider;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.axonivy.connectivity.rest.json.BackendJsonMapperCustomization;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Demonstrates how to customize the JSON output for objects with a global {@link Provider}
 * It's defined in the {@link BackendJsonMapperCustomization}.
 */
@Path("dates")
@Tag(name = ApiConstants.DEMO_TAG)
public class DateResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Shipping ship() {
    return new Shipping();
  }

  public static class Shipping {
    public String what = "Ferrari";
    public Date delivery = Calendar.getInstance().getTime();
  }

}
