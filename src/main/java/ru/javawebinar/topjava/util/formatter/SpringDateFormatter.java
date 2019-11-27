package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.util.Locale;

import static ru.javawebinar.topjava.util.DateTimeUtil.DATE_FORMATTER;

public class SpringDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) {
        return text.isEmpty() ? null : LocalDate.parse(text, DATE_FORMATTER);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return object == null ? "" : object.format(DATE_FORMATTER);
    }
}
