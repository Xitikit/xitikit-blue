package org.xitikit.azureadb2c.common.converters.test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.Test;
import org.mockito.Mockito;
import org.xitikit.azureadb2c.common.converters.UtcUnixDateDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes *
 */
public class UtcUnixDateDeserializerTest {

    /**
     * Make sure values being deserialized remain of the same value.
     *
     * @throws IOException Because it does.
     */
    @Test
    public void deserialize() throws IOException {

        JsonParser jsonParser = Mockito.mock(JsonParser.class);
        DeserializationContext deserializationContext = Mockito.mock(DeserializationContext.class);
        Long unixTimeInput = System.currentTimeMillis() / 1000L;

        when(deserializationContext.readValue(any(JsonParser.class), Long.class)).thenReturn(unixTimeInput);

        LocalDateTime result = new UtcUnixDateDeserializer().deserialize(jsonParser, deserializationContext);
        assertTrue(result != null && result.toEpochSecond(ZoneOffset.UTC) == unixTimeInput);
    }
}
