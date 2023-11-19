package com.bs.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p,
                                     DeserializationContext ctxt) throws IOException {
        String dateTimeAsString = p.getText();
        return OffsetDateTime.parse(dateTimeAsString, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalDateTime();
    }
}
