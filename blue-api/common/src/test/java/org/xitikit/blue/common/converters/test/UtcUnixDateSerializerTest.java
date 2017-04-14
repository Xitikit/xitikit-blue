package org.xitikit.blue.common.converters.test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;
import org.mockito.Mockito;
import org.xitikit.blue.common.converters.UtcUnixDateSerializer;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Copyright Xitikit.org 2017
 * @author J. Keith Hoopes
 */
public class UtcUnixDateSerializerTest {

    /**
     * This could be improved. Right now it is just
     * making sure there are no null pointers and that
     * the expected parameters are correct.
     *
     * @throws IOException Because reasons exist.
     */
    @Test
    public void serialize() throws IOException {

        new UtcUnixDateSerializer().serialize(
                LocalDateTime.now(),
                Mockito.mock(JsonGenerator.class),
                Mockito.mock(SerializerProvider.class));
    }
}
