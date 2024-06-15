package com.example.demo.test;


import com.example.demo.config.CustomLocalDateDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomLocalDateDeserializerTest {

    private CustomLocalDateDeserializer deserializer;

    @BeforeEach
    public void setUp() {
        deserializer = new CustomLocalDateDeserializer();
    }

    @Test
    public void testDeserialize() throws IOException {
        String dateStr = "Jan-01-2023";
        JsonParser jsonParser = mock(JsonParser.class);
        DeserializationContext context = mock(DeserializationContext.class);

        when(jsonParser.getText()).thenReturn(dateStr);

        LocalDate result = deserializer.deserialize(jsonParser, context);
        LocalDate expected = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("MMM-dd-yyyy"));

        assertEquals(expected, result);
    }
}
