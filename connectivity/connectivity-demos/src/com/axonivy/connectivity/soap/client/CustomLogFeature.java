package com.axonivy.connectivity.soap.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.http.HttpStatus;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.webservice.exec.feature.WebServiceClientFeature;
import ch.ivyteam.ivy.webservice.exec.feature.WebServiceClientFeatureContext;

/**
 * Demonstrates the setup of custom interceptors that track
 * the messages going out to the remote service and their responses.
 */
public class CustomLogFeature implements WebServiceClientFeature {

  private static final String EXCHANGE_ID_KEY = "exchangeId";

  @Override
  public void initialize(WebServiceClientFeatureContext context) {
    Client proxy = ClientProxy.getClient(context.getBindingProvider());
    proxy.getOutInterceptors().add(new CxfSendInterceptor(Phase.SEND_ENDING));
    proxy.getInInterceptors().add(new CxfReceiveInterceptor(Phase.RECEIVE));
    Ivy.log().info("call initiated with custom interceptors provided by " + this);
  }

  public static class CxfSendInterceptor extends AbstractPhaseInterceptor<Message> {

    public CxfSendInterceptor(String phase) {
      super(phase);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
      String exchangeId = getExchangeKey(message);
      Ivy.session().setAttribute("lastExchange", exchangeId);
      Ivy.log().debug("started exchange " + exchangeId);
    }

  }

  public class CxfReceiveInterceptor extends AbstractPhaseInterceptor<Message> {

    public CxfReceiveInterceptor(String phase) {
      super(phase);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
      int responseCode = (int) message.get(Message.RESPONSE_CODE);
      Ivy.log().debug("ended exchange " + getExchangeKey(message));
      if (HttpStatus.SC_OK != responseCode) {
        Ivy.log().error("request failed with http error code " + responseCode);
      }
    }
  }

  private static String getExchangeKey(Message message) {
    return (String) message.getExchange().get(EXCHANGE_ID_KEY);
  }
}
