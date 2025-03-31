package com.axonivy.connectivity.rest;

import static com.axonivy.ivy.webtest.engine.EngineUrl.createProcessUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

import java.net.MalformedURLException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;

import soap.bpm.TestDataMapping.Smartbear;

@IvyWebTest
public class WebTestRestProcessStarts {

  @Test
  void checkAllSimpleRestCalls() {
    checkProcess("16764B07C0329FC8/callBatchAsync.ivp");
    checkProcess("169B3B7F5EF38D8F/upAndDown.ivp");
    checkProcess("169B3B7F5EF38D8F/fileMultipart.ivp");
    checkProcess("169B3B7F5EF38D8F/multipleFiles.ivp");
  }

  @Test
  void checkAllSimpleSoapCalls() throws Exception {
    enableSmartbearMock();
    checkProcess("1605A38503199ADB/resolveToCache.ivp");
    checkProcess("1605A38503199ADB/mapComplexData.ivp");
    checkProcess("16150E26E34D4339/read.ivp");
    checkProcess("16150E26E34D4339/add.ivp");
    checkProcess("16150E26E34D4339/delete.ivp");
  }

  private void enableSmartbearMock() throws MalformedURLException {
    var procUrl = createProcessUrl("connectivity-demos-test/180D6E5562D69BF0/configure.ivp");
    var engineProcUri = URI.create(procUrl.replaceAll("localhost", System.getProperty("test.host.name", "localhost")));
    URI configure = UriBuilder
        .fromUri(engineProcUri)
        .queryParam("key", Smartbear.ENDPOINT_URI_KEY)
        .queryParam("value", Smartbear.MOCK_SERVICE).build();
    open(configure.toURL());
  }

  private void checkProcess(String process) {
    String path = "connectivity-demos/" + process;
    var procUrl = createProcessUrl(path).replaceAll("localhost", System.getProperty("test.host.name", "localhost"));
    open(procUrl);
    webdriver().shouldHave(urlContaining("endedTaskId="));
  }
}
