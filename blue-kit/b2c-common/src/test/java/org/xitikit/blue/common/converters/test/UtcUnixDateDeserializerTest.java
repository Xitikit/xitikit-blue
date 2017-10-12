package org.xitikit.blue.common.converters.test;

/**
 * Copyright Xitikit.org 2017
 *
 * @author J. Keith Hoopes *
 */
public class UtcUnixDateDeserializerTest{

  //    /**
  //     * Make sure values being deserialized remain of the same value.
  //     *
  //     * @throws IOException Because it does.
  //     */
  //    @Test
  //    public void deserialize() throws IOException {
  //
  //        JsonParser jsonParser = Mockito.mock(JsonParser.class);
  //        DeserializationContext deserializationContext = Mockito.mock(DeserializationContext.class);
  //        Long unixTimeInput = System.currentTimeMillis() / 1000L;
  //
  //        when(deserializationContext.readValue(jsonParser, Long.class)).thenReturn(unixTimeInput);
  //
  //        LocalDateTime result = new UtcUnixDateDeserializer().deserialize(jsonParser, deserializationContext);
  //        assertTrue(result != null && result.toEpochSecond(ZoneOffset.UTC) == unixTimeInput);
  //    }
}
