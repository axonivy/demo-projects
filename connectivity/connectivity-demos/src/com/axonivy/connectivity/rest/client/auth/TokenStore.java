package com.axonivy.connectivity.rest.client.auth;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISession;

public class TokenStore {
  private final ISession session;
  private final String service;
  private final Logger log;

  public static TokenStore get(String service) {
    return new TokenStore(Ivy.session(), service, Ivy.log());
  }

  public TokenStore(ISession session, String service, Logger log) {
    this.session = session;
    this.service = service;
    this.log = log;
  }

  public boolean hasToken() {
    return getToken() != null;
  }

  public void setToken(JsonNode token) {
    log.debug("storing service-token for '" + service + "' on session '" + session + "' value: " + token);
    session.setAttribute(service, token);
  }

  public JsonNode getToken() {
    JsonNode token = (JsonNode) session.getAttribute(service);
    log.debug("reading service-token for '" + service + "' on session '" + session + "' value: " + token);
    return token;
  }

}
