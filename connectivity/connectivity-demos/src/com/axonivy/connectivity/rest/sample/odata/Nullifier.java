package com.axonivy.connectivity.rest.sample.odata;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

/**
 * Copied from {@link NullifyingDeserializer}, but enriched with generic type to comply with SimpleModule api.
 */
public class Nullifier<T> extends StdDeserializer<T> {
  private static final long serialVersionUID = 1L;

  public Nullifier(Class<T> type) {
    super(type);
  }

  @Override // since 2.9
  public Boolean supportsUpdate(DeserializationConfig config) {
    return Boolean.FALSE;
  }

  @Override
  public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    if (p.hasToken(JsonToken.FIELD_NAME)) {
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
      TypeDeserializer typeDeserializer) throws IOException {
    return switch (p.currentTokenId()) {
      case JsonTokenId.ID_START_ARRAY, JsonTokenId.ID_START_OBJECT, JsonTokenId.ID_FIELD_NAME -> (T) typeDeserializer.deserializeTypedFromAny(p, ctxt);
      default -> null;
    };
  }
}
