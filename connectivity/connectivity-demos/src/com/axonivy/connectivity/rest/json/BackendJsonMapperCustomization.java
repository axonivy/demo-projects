package com.axonivy.connectivity.rest.json;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.Priority;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Overwrites the default serialization of 'Date' for the Rest resources provided in your projects.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Priority(value = Integer.MAX_VALUE)
public class BackendJsonMapperCustomization implements ContextResolver<ObjectMapper> {

  @Override
  public ObjectMapper getContext(Class<?> type) {
    System.out.println("customizing mapper for type " + type);
    final ObjectMapper om = new ObjectMapper();
    final SimpleModule module = new SimpleModule();
    module.addSerializer(Date.class, DateTimeConverterSerializer.ISO_DATE_TIME_UTC);
    om.registerModule(module);
    System.out.println("mapping is customized " + om + " using " + this);
    return om;
  }

  public static class DateTimeConverterSerializer extends JsonSerializer<Date> {

    public static DateTimeConverterSerializer ISO_DATE_TIME_UTC = new DateTimeConverterSerializer(
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"), ZoneId.of("Z"));

    private final DateFormat dateTimeFormatter;

    public DateTimeConverterSerializer(final DateFormat dateTimeFormatter, ZoneId zoneId) {
      final DateFormat df = (DateFormat) dateTimeFormatter.clone();
      df.setTimeZone(TimeZone.getTimeZone(zoneId));
      this.dateTimeFormatter = df;
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      System.out.println("customizing time (" + value + ") using " + this + "!");
      gen.writeString(dateTimeFormatter.format(value));
    }
  }
}
