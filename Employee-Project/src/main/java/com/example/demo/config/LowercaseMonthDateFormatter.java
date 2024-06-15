package com.example.demo.config;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class LowercaseMonthDateFormatter implements Formatter<LocalDate> {

    private final DateTimeFormatter formatter;

    public LowercaseMonthDateFormatter() {
        this.formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy", Locale.ENGLISH);
    }

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        text = capitalizeMonth(text);
        return LocalDate.parse(text, formatter);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return object.format(formatter);
    }

    private String capitalizeMonth(String date) {
        String[] parts = date.split("-");
        if (parts.length == 3) {
            parts[0] = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1).toLowerCase();
        }
        return String.join("-", parts);
    }
}
