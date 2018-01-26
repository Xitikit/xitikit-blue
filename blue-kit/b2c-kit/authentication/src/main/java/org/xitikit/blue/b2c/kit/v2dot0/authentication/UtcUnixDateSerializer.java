package org.xitikit.blue.b2c.kit.v2dot0.authentication;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZonedDateTime;

/**
 * Copyright Xitikit.org 2017
 *
 * A Serializer for dates received from the Graph API.
 * Azure AD B2C uses UTC Unix formatted dates while the
 * default behaviour of the jackson JSON deserializers are
 * to use the time in milliseconds from epoch.
 *
 * @author J. Keith Hoopes
 */
public class UtcUnixDateSerializer extends JsonSerializer<ZonedDateTime>{

  /**
   * Converts a ZonedDateTime into a long equal to the seconds
   * from "January 1, 1970" and writes it out
   * to the given JsonGenerator.
   *
   * @param date ZonedDateTime
   * @param jsonGenerator JsonGenerator
   * @param serializerProvider SerializerProvider
   *
   * @throws IOException @see JsonGenerator.writeNumber(...)
   */
  @Override
  public void serialize(
    final ZonedDateTime date,
    final JsonGenerator jsonGenerator,
    final SerializerProvider serializerProvider) throws IOException{

    jsonGenerator.writeNumber(date.toEpochSecond());
  }
}
