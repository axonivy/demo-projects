package com.axonivy.connectivity.rest.sample.odata;

import java.io.IOException;

import javax.ws.rs.Priorities;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.MediaType;

import org.odata.trippin.client.AnyOfMicrosoftODataSampleServiceModelsTripPinPersonConcurrency;
import org.odata.trippin.client.AnyOfMicrosoftODataSampleServiceModelsTripPinPersonGender;

import com.axonivy.connectivity.rest.json.OpenApiJsonFeature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * Some values in the converted TripPin ODATA spec are generated as empty interfaces without a matching impl.
 *
 * So let's handle them to make object transformation possible.
 *
 * @since 9.2
 */
public class TripPinJsonFeature extends OpenApiJsonFeature {
  @Override
  public boolean configure(FeatureContext context) {
    JacksonJsonProvider provider = new TripPinJson();
    configure(provider, context.getConfiguration());
    context.register(provider, Priorities.ENTITY_CODER);
    return true;
  }

  private static class TripPinJson extends JaxRsClientJson {
    @Override
    public ObjectMapper locateMapper(Class<?> type, MediaType mediaType) {
      var mapper = super.locateMapper(type, mediaType);
      mapper.registerModule(new TripPinTypeCustomizations());
      return mapper;
    }
  }

  private static class TripPinTypeCustomizations extends SimpleModule {
    private static final long serialVersionUID = 4552540562745977391L;

    public TripPinTypeCustomizations() {
      nullify(AnyOfMicrosoftODataSampleServiceModelsTripPinPersonConcurrency.class); // ignore 'Concurrency' field.
      addDeserializer(AnyOfMicrosoftODataSampleServiceModelsTripPinPersonGender.class, new GenderDeserializer());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void nullify(Class<?> raw) {
      addDeserializer(raw, new Nullifier(raw));
    }
  }

  private static class GenderDeserializer extends StdDeserializer<AnyOfMicrosoftODataSampleServiceModelsTripPinPersonGender> {
    private static final long serialVersionUID = 8173333520337377195L;

    public GenderDeserializer() {
      super(AnyOfMicrosoftODataSampleServiceModelsTripPinPersonGender.class);
    }

    @Override
    public AnyOfMicrosoftODataSampleServiceModelsTripPinPersonGender deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
      return new GenderWrapper(p.getText());
    }
  }

  /**
   * simple custom created string wrapper: since generated JAX-RS client interface has no valid impl.
   */
  public static class GenderWrapper implements AnyOfMicrosoftODataSampleServiceModelsTripPinPersonGender {
    private final String gender;

    public GenderWrapper(String gender) {
      this.gender = gender;
    }

    @Override
    public String toString() {
      return gender;
    }
  }
}
