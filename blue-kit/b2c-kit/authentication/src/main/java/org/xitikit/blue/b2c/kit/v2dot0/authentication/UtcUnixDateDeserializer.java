package org.xitikit.blue.b2c.kit.v2dot0.authentication;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Copyright Xitikit.org 2017
 *
 * A Deserializer for dates received from the Graph API.
 * Azure AD B2C uses UTC Unix formatted dates while the
 * default behaviour of the jackson JSON deserializers is
 * to use the time in milliseconds from epoch.
 *
 * @author J. Keith Hoopes
 */
public class UtcUnixDateDeserializer extends JsonDeserializer<ZonedDateTime>{

  /**
   * Converts the value from the DeserializationContext
   * to a LocalDateTime.
   *
   * Assumes the value being passed is a Long with a value that
   * represents the number of seconds since January 1 2017.
   *
   * @param jsonParser JsonParser
   * @param deserializationContext DeserializationContext
   *
   * @return ZonedDateTime
   *
   * @throws IOException when the value cannot be read, or is improperly formatted.
   */
  @Override
  public ZonedDateTime deserialize(
    final JsonParser jsonParser,
    final DeserializationContext deserializationContext) throws IOException{

    return Instant
             .ofEpochSecond(
               deserializationContext
                 .readValue(
                   jsonParser,
                   Long.class))
             .atZone(
               ZoneId.systemDefault());
  }
}
