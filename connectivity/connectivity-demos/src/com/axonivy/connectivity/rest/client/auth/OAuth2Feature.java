package com.axonivy.connectivity.rest.client.auth;

import java.io.IOException;

import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import jakarta.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;

import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISession;
import ch.ivyteam.log.Logger;

public class OAuth2Feature implements Feature {
  public static final String TOKEN_MISSING = "rest:client:token:missing";
  public static final String SERVICE_ID = "serviceId";

  @Override
  public boolean configure(FeatureContext context) {
    context.register(new AuthorizationFilter(Ivy.session(), Ivy.log()), Priorities.AUTHORIZATION);
    return true;
  }

  @Provider
  private static class AuthorizationFilter implements jakarta.ws.rs.client.ClientRequestFilter {
    private final ISession session;
    private final Logger logger;

    public AuthorizationFilter(ISession session, ch.ivyteam.log.Logger logger) {
      this.session = session;
      this.logger = logger;
    }

    @Override
    public void filter(ClientRequestContext context) throws IOException {
      String service = context.getUri().getHost();
      TokenStore store = new TokenStore(session, service, logger);
      JsonNode token = store.getToken();
      if (token == null) {
        throw BpmError.create(TOKEN_MISSING)
            .withAttribute(SERVICE_ID, service)
            .build();
      }
      String accessToken = token.get("access_token").asText();
      if (StringUtils.isEmpty(accessToken)) {
        throw new IllegalStateException("Failed to read 'access_token' from " + token);
      }
      context.getHeaders().add("Authorization", "Bearer " + accessToken);
    }
  }

}
