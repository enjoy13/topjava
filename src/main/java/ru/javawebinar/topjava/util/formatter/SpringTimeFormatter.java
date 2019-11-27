package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;

import java.time.LocalTime;
import java.util.Locale;

import static ru.javawebinar.topjava.util.DateTimeUtil.TIME_FORMATTER;

public class SpringTimeFormatter implements Formatter<LocalTime> {

    @Override
    public LocalTime parse(String text, Locale locale) {
        return text.isEmpty() ? null : LocalTime.parse(text, TIME_FORMATTER);
    }

    @Override
    public String print(LocalTime object, Locale locale) {
        return object == null ? "" : object.format(TIME_FORMATTER);
    }
}
