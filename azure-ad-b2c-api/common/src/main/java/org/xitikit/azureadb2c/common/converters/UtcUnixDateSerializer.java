package org.xitikit.azureadb2c.common.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Copyright Xitikit.org 2017
 * <p>
 * A Serializer for dates received from the Graph API.
 * Azure AD B2C uses UTC Unix formatted dates while the
 * default behaviour of the jackson JSON deserializers are
 * to use the time in milliseconds from epoch.
 *
 * @author J. Keith Hoopes
 */
public class UtcUnixDateSerializer extends JsonSerializer<LocalDateTime> {

    /**
     * Converts a LocalDateTime into a long equal to the seconds
     * from January 1, 1970 in UTC time zone, and writes it out
     * to the given JsonGenerator.
     *
     * @param date LocalDateTime
     * @param jsonGenerator JsonGenerator
     * @param serializerProvider SerializerProvider
     * @throws IOException @see JsonGenerator.writeNumber(...)
     */
    @Override
    public void serialize(LocalDateTime date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeNumber(date.toEpochSecond(ZoneOffset.UTC));
    }
}
