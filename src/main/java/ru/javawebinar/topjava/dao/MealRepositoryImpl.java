package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealRepositoryImpl implements MealRepository {
    private List<Meal> mealsList = Arrays.asList(
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 5, 10, 0), "Dinner", 300),
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 5, 14, 0), "Lunch", 1000),
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 5, 18, 0), "Breakfast", 700),
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 6, 9, 0), "Dinner", 300),
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 6, 13, 0), "Lunch", 1100),
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 6, 17, 0), "Breakfast", 700),
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 7, 8, 0), "Dinner", 300),
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 7, 12, 0), "Lunch", 1000),
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 7, 18, 0), "Breakfast", 700),
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 8, 11, 0), "Dinner", 300),
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 8, 15, 0), "Lunch", 1100),
            new Meal(LocalDateTime.of(2019, Month.NOVEMBER, 8, 20, 0), "Breakfast", 700)
    );

    @Override
    public List<Meal> findAllMealTo() {
        return mealsList;
    }
}
