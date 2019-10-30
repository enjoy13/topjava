package ru.javawebinar.topjava.util;

import org.springframework.lang.Nullable;

public final class Util {

    private Util() {
        throw new AssertionError();
    }

    public static <T extends Comparable<? super T>> boolean isBetweenInclusive(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) <= 0);
    }
}
