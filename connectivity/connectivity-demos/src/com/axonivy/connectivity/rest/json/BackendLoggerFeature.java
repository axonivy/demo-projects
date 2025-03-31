package com.axonivy.connectivity.rest.json;

import java.util.logging.Logger;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

/**
 * Enables logging for the REST backend.
 */
@Provider
public class BackendLoggerFeature implements Feature {

  private static final Logger LOGGER = Logger.getLogger(BackendLoggerFeature.class.getName());

  @Override
  @SuppressWarnings("all")
  public boolean configure(FeatureContext context) {
    System.out.println("Installing custom loggingg feature for REST backend " + BackendLoggerFeature.class);
    context.register(new org.glassfish.jersey.filter.LoggingFilter(LOGGER, true));
    return true;
  }

}
