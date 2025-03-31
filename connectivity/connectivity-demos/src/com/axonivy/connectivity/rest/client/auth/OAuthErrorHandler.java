package com.axonivy.connectivity.rest.client.auth;

import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.util.ExceptionUtil;

public class OAuthErrorHandler {

  public static boolean isAuthError(BpmError error) {
    Throwable cause = error.getTechnicalCause();
    BpmError wrappedError = ExceptionUtil.getCause(cause, BpmError.class);
    if (wrappedError != null) {
      if (OAuth2Feature.TOKEN_MISSING.equals(wrappedError.getErrorCode())) {
        return true;
      } else {
        throw wrappedError;
      }
    }
    throw error;
  }

}
