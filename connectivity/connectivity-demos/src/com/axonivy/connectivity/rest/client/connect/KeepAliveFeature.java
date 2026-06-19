package com.axonivy.connectivity.rest.client.connect;

import java.util.Optional;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;

import org.apache.hc.core5.http.HeaderElement;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.client5.http.ConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.message.BasicHeaderElementIterator;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;

/**
 * Demonstrates the setup of a custom KeepAlive strategy for established connections.
 * https://hc.apache.org/httpcomponents-client-4.5.x/current/tutorial/html/connmgmt.html#d5e425
 *
 * <p>This is useful to enable, if the remote service doesn't specify a keep-alive time,
 * but nevertheless finishes connections after a certain amount of time.</p>
 *
 * <b>WARNING: this only works in combination with the default Apache5ConnectorProvider</b>
 */
public class KeepAliveFeature implements Feature {

  @Override
  public boolean configure(FeatureContext context) {
    // see Apache5ClientProperties.KEEPALIVE_STRATEGY
    String keepAlive = "jersey.config.apache.client.keepAliveStrategy";
    context.property(keepAlive, new CustomKeepAlive());
    return true;
  }

  private static class CustomKeepAlive implements ConnectionKeepAliveStrategy {

    @Override
    public TimeValue getKeepAliveDuration(HttpResponse response, HttpContext context) {
      return parseHeader(response).orElseGet(() -> customTimeout(context));
    }

    private Optional<TimeValue> parseHeader(HttpResponse response) {
      var it = new BasicHeaderElementIterator(response.headerIterator("Keep-Alive"));
      while (it.hasNext()) {
        HeaderElement he = it.next();
        String param = he.getName();
        String value = he.getValue();
        if (value != null && "timeout".equalsIgnoreCase(param)) {
          try {
            var timeout = TimeValue.ofSeconds(Long.parseLong(value));
            return Optional.of(timeout);
          } catch (NumberFormatException ignore) {}
        }
      }
      return Optional.empty();
    }

    private TimeValue customTimeout(HttpContext context) {
      HttpHost target = HttpClientContext.adapt(context).getHttpRoute().getTargetHost();
      if ("www.naughty-server.com".equalsIgnoreCase(target.getHostName())) {
        // Keep alive for 5 seconds only
        return TimeValue.ofSeconds(5);
      }
      // otherwise keep alive for 30 seconds
      return TimeValue.ofSeconds(30);
    }

  }

}
