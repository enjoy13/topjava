package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.util.Locale;

public class SpringDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) {
        return text.isEmpty() ? null : LocalDate.parse(text);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return object == null ? "" : object.toString();
    }
}
