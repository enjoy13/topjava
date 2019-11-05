package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;
import ru.javawebinar.topjava.Profiles;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ConverterToLocalDateTime implements Converter {
    @Override
    public Object convert(Object source) {
        return Profiles.getActiveDbProfile().equals(Profiles.HSQL_DB) ? Timestamp.valueOf((LocalDateTime) source) : source ;
    }
}
