package com.axonivy.connectivity.rest.sample.odata;

import org.odata.trippin.client.AnyOfMicrosoftODataSampleServiceModelsTripPinPersonConcurrency;
import org.odata.trippin.client.AnyOfMicrosoftODataSampleServiceModelsTripPinPersonGender;

import com.axonivy.connectivity.rest.json.OpenApiJsonFeature;

import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.FeatureContext;
import jakarta.ws.rs.core.MediaType;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.deser.std.StdDeserializer;
import tools.jackson.databind.json.JsonMapper;

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
    var provider = new TripPinJson();
    configure(provider, context.getConfiguration());
    context.register(provider, Priorities.ENTITY_CODER);
    return true;
  }

  private static class TripPinJson extends JaxRsClientJson {
    @Override
    public JsonMapper locateMapper(Class<?> type, MediaType mediaType) {
      return super.locateMapper(type, mediaType).rebuild()
        .addModule(new TripPinTypeCustomizations())
        .build();
    }
  }

  private static class TripPinTypeCustomizations extends tools.jackson.databind.module.SimpleModule {
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

    public GenderDeserializer() {
      super(AnyOfMicrosoftODataSampleServiceModelsTripPinPersonGender.class);
    }

    @Override
    public AnyOfMicrosoftODataSampleServiceModelsTripPinPersonGender deserialize(tools.jackson.core.JsonParser p,
        tools.jackson.databind.DeserializationContext ctxt) throws JacksonException {
      return new GenderWrapper(p.getString());
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
