package org.xitikit.azureadb2c.common.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Copyright Xitikit.org 2017
 * <p>
 * A Deserializer for dates received from the Graph API.
 * Azure AD B2C uses UTC Unix formatted dates while the
 * default behaviour of the jackson JSON deserializers is
 * to use the time in milliseconds from epoch.
 *
 * @author J. Keith Hoopes
 */
public class UtcUnixDateDeserializer extends JsonDeserializer<LocalDateTime> {

    /**
     * Converts the value from the DeserializationContext
     * to a LocalDateTime.
     * <p>
     * Assumes the value being passed is a Long with a value that
     * represents the number of seconds since January 1 2017.
     *
     * @param jsonParser             JsonParser
     * @param deserializationContext DeserializationContext
     * @return LocalDateTime
     * @throws IOException when the value cannot be read, or is improperly formatted.
     */
    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        return Instant.ofEpochMilli(deserializationContext.readValue(jsonParser, Long.class) * 1000L).atZone(ZoneId.of("UTC")).toLocalDateTime();
    }
}
