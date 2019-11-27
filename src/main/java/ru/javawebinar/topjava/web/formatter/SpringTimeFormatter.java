package ru.javawebinar.topjava.web.formatter;

import org.springframework.format.Formatter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SpringTimeFormatter implements Formatter<LocalTime> {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public LocalTime parse(String text, Locale locale) {
        return text.isEmpty() ? null : LocalTime.parse(text, TIME_FORMATTER);
    }

    @Override
    public String print(LocalTime object, Locale locale) {
        return object == null ? "" : object.format(TIME_FORMATTER);
    }
}
