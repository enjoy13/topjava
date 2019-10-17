package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {

    Meal save(Integer userId, Meal meal);
    boolean delete(Integer userId, Integer mealId);
    Meal get(Integer userId, Integer mealId);
    List<Meal> getAll(Integer userId);
    List<Meal> getFilteredByDate(Integer userId, LocalDate startTime, LocalDate endTime);

}
