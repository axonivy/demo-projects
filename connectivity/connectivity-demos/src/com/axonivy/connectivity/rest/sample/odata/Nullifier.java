package com.axonivy.connectivity.rest.sample.odata;

import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;
import tools.jackson.core.JsonTokenId;
import tools.jackson.databind.DeserializationConfig;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.jsontype.TypeDeserializer;

/**
 * Copied from NullifyingDeserializer, but enriched with generic type to comply with SimpleModule api.
 */
public class Nullifier<T> extends tools.jackson.databind.deser.std.StdDeserializer<T> {

  public Nullifier(Class<T> type) {
    super(type);
  }

  @Override // since 2.9
  public Boolean supportsUpdate(DeserializationConfig config) {
    return Boolean.FALSE;
  }

  @Override
  public T deserialize(JsonParser p, DeserializationContext ctxt) {
    if (p.hasToken(JsonToken.PROPERTY_NAME)) {
      while (true) {
        JsonToken t = p.nextToken();
        if ((t == null) || (t == JsonToken.END_OBJECT)) {
          break;
        }
        p.skipChildren();
      }
    } else {
      p.skipChildren();
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public T deserializeWithType(JsonParser p, DeserializationContext ctxt,
      TypeDeserializer typeDeserializer) {
    return switch (p.currentTokenId()) {
      case JsonTokenId.ID_START_ARRAY, JsonTokenId.ID_START_OBJECT, JsonTokenId.ID_PROPERTY_NAME -> (T) typeDeserializer.deserializeTypedFromAny(p, ctxt);
      default -> null;
    };
  }
}
