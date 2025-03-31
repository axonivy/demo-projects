package com.axonivy.connectivity.soap;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.engine.EngineUrl;

class IntegrationTestSoapIgnorePolicy {
  private static final String PROCESS_ID = "162AFFB8ACCCB74C";

  @Test
  void greetWithPolicyDisabled() throws Exception {
    var url = EngineUrl.createProcessUrl("/connectivity-demos-test/" + PROCESS_ID + "/greeterPoliciesDisabled.ivp");
    var httpRequest = HttpRequest.newBuilder(URI.create(url)).build();
    var content = HttpClient.newHttpClient().send(httpRequest, BodyHandlers.ofString()).body();
    assertThat(content).isEqualTo("org.apache.cxf.binding.soap.SoapFault: A security error was encountered when verifying the message");
  }
}
