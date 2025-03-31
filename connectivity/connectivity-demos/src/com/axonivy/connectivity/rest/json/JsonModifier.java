package com.axonivy.connectivity.rest.json;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * JSON to POJO mapper designed to apply JSON structure changes before processing
 * as usual.
 *
 * @since 8.0.0
 * @deprecated since 9.2, where such manual JSON transformations are no longer necessary.
 *             Instead of applying payload transformations, one can convert ODATA$metadata to Open API 3.0
 *             and use it directly with full type support on Rest Client call elements.
 *             https://github.com/ivy-samples/ivy-project-demos/blob/playground/odataConvert/connectivity/odata-converter/convert.sh
 */
@Deprecated
public class JsonModifier extends JacksonJsonProvider {
  private static final ObjectMapper ROOT_MAPPER = new ObjectMapper();

  @Override
  public Object readFrom(Class<Object> type, Type genericType,
      Annotation[] annotations, MediaType mediaType,
      MultivaluedMap<String, String> httpHeaders,
      InputStream entityStream) throws IOException {
    InputStream inputStream = unwrapValueRoot(entityStream);
    return super.readFrom(type, genericType, annotations, mediaType, httpHeaders, inputStream);
  }

  protected InputStream unwrapValueRoot(InputStream entityStream)
      throws IOException, JsonProcessingException {
    JsonNode node = ROOT_MAPPER.readTree(entityStream);
    node = manipulateJson(node);
    String json = ROOT_MAPPER.writeValueAsString(node);
    return IOUtils.toInputStream(json, Charset.defaultCharset());
  }

  protected JsonNode manipulateJson(JsonNode node) {
    return node;
  }
}
