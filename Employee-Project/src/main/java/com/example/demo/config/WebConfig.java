package com.example.demo.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LowercaseMonthDateFormatter lowercaseMonthDateFormatter;

    public WebConfig(LowercaseMonthDateFormatter lowercaseMonthDateFormatter) {
        this.lowercaseMonthDateFormatter = lowercaseMonthDateFormatter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(lowercaseMonthDateFormatter);
    }
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        
        // Register custom deserializer for LocalDate
        javaTimeModule.addDeserializer(LocalDate.class, new CustomLocalDateDeserializer());
        
        mapper.registerModule(javaTimeModule);
        return mapper;
    }
}
