package org.xitikit.azureadb2c.common.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
public class UtcUnixDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        Long unixTime = deserializationContext.readValue(jsonParser, Long.class);
        return new Date(unixTime * 1000L);
    }
}
