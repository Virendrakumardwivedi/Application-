package com.example.demo.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private final DateTimeFormatter formatter;

    public CustomLocalDateDeserializer() {
        this.formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy", Locale.ENGLISH);
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String date = p.getText().trim();
        date = capitalizeMonth(date);
        return LocalDate.parse(date, formatter);
    }

    private String capitalizeMonth(String date) {
        String[] parts = date.split("-");
        if (parts.length == 3) {
            parts[0] = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1).toLowerCase();
        }
        return String.join("-", parts);
    }
}

