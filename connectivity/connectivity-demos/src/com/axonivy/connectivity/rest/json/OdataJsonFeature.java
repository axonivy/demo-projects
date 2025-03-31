package com.axonivy.connectivity.rest.json;

import javax.ws.rs.Priorities;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import ch.ivyteam.ivy.rest.client.mapper.JsonFeature;

/**
 * Simple mapper that can consume OData services. <br/>
 * This mapper is a <b>sample implementation</b> that covers some often faced
 * field problems while consuming OData services.<br/>
 * There is no intention that this mapper completely covers all OData
 * specifications.
 *
 * <p>
 * Sample JSON input:
 * </p>
 *
 * <pre>
 * {
 * &nbsp;&nbsp;"@odata.context":"http://services.odata.org/TripPinRESTierService/(S(5jklwgt1fhflbw1mxbymqdqe))/$metadata#People",
 * &nbsp;&nbsp;"value":[{"UserName":"russellwhyte","FirstName":"Russell,...},{...}]
 * }"
 * </pre>
 *
 * <p>
 * Sample JSON output:
 * </p>
 *
 * <pre>
 * [{"UserName":"russellwhyte","FirstName":"Russell,...},{...}]
 * </pre>
 *
 * <p>
 * Sample service:
 * http://services.odata.org/TripPinRESTierService/(S(5jklwgt1fhflbw1mxbymqdqe))/People
 * </p>
 * @see "http://www.odata.org/"
 *
 * @since 6.7.1
 * @deprecated since 9.2, where such manual JSON transformations are no longer necessary.
 *             Instead of applying payload transformations, one can convert ODATA$metadata to Open API 3.0
 *             and use it directly with full type support on Rest Client call elements.
 *             https://github.com/ivy-samples/ivy-project-demos/blob/playground/odataConvert/connectivity/odata-converter/convert.sh
 */
@Deprecated
public class OdataJsonFeature extends JsonFeature {
  @Override
  public boolean configure(FeatureContext context) {
    JacksonJsonProvider provider = new ODataMapperProvider();
    configure(provider, context.getConfiguration());
    context.register(provider, Priorities.ENTITY_CODER);
    return true;
  }

  public static class ODataMapperProvider extends JsonModifier {
    @Override
    public ObjectMapper locateMapper(Class<?> type, MediaType mediaType) {
      ObjectMapper mapper = super.locateMapper(type, mediaType);
      // odata provides fields starting with an upper case character!
      mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
      return mapper;
    }

    /**
     * unwrap collections which store their value array in an internal 'value'
     * field.
     */
    @Override
    protected JsonNode manipulateJson(JsonNode node) {
      if (node.has(Field.VALUE) && node.has(Field.CONTEXT)) {
        node = node.get(Field.VALUE);
      }
      return node;
    }

    /** Well known OData fields */
    private interface Field {
      String VALUE = "value";
      String CONTEXT = "@odata.context";
    }
  }
}
