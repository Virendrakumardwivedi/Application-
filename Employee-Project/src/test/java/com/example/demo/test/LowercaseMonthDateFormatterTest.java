package com.example.demo.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.config.LowercaseMonthDateFormatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LowercaseMonthDateFormatterTest {

    private LowercaseMonthDateFormatter formatter;

    @BeforeEach
    public void setUp() {
        formatter = new LowercaseMonthDateFormatter();
    }

    @Test
    public void testParse() throws ParseException {
        String dateStr = "Jan-01-2023";
        LocalDate expectedDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        LocalDate parsedDate = formatter.parse(dateStr, null);

        assertEquals(expectedDate, parsedDate);
    }

    @Test
    public void testPrint() {
        LocalDate date = LocalDate.of(2023, 1, 1);
        String expectedDateStr = "Jan-01-2023";
        String printedDate = formatter.print(date, null);

        assertEquals(expectedDateStr, printedDate);
    }
}

