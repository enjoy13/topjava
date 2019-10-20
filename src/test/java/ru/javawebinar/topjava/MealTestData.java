package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_ID = START_SEQ + 2;
    public static LocalDate START_DATE_TEST = LocalDate.of(2019, 10, 17);
    public static LocalDate END_DATE_TEST = LocalDate.of(2019, 10, 19);

    public static final Meal MEAL_TEST_FIRST =
            new Meal(MEAL_ID, DateTimeUtil.parseLocalDateTime("2019-10-18 09:00"), "testFirst", 5000);

    public static final Meal MEAL_TEST_SECOND =
            new Meal(MEAL_ID + 1, DateTimeUtil.parseLocalDateTime("2019-10-20 09:01"), "testSecond", 7000);
}
