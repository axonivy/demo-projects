package com.axonivy.connectivity.rest.client.connect;

import java.util.Optional;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;

import org.apache.hc.core5.http.HeaderElement;
import org.apache.hc.core5.http.HeaderElementIterator;
import org.apache.hc.core5.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.hc.core5.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;

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
    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
      return parseHeader(response).orElseGet(() -> customTimeout(context));
    }

    private Optional<Long> parseHeader(HttpResponse response) {
      HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
      while (it.hasNext()) {
        HeaderElement he = it.nextElement();
        String param = he.getName();
        String value = he.getValue();
        if (value != null && "timeout".equalsIgnoreCase(param)) {
          try {
            var timeout = Long.parseLong(value) * 1000;
            return Optional.of(timeout);
          } catch (NumberFormatException ignore) {}
        }
      }
      return Optional.empty();
    }

    private long customTimeout(HttpContext context) {
      HttpHost target = (HttpHost) context.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);
      if ("www.naughty-server.com".equalsIgnoreCase(target.getHostName())) {
        // Keep alive for 5 seconds only
        return 5 * 1000;
      }
      // otherwise keep alive for 30 seconds
      return 30 * 1000;
    }

  }

}
