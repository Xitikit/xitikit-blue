package org.xitikit.blue.authorization.azure.ad.b2c.openidconnect.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @author J. Keith Hoopes
 *         Copyright Xitikit.org 2017
 */
public class UtcUnixDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        long epochTime = date.getTime();
        long unixTime = epochTime / 1000L;

        jsonGenerator.writeNumber(unixTime);
    }
}
