package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MealRepository {

    Meal save(Integer userId, Meal meal);
    boolean delete(Integer userId, Integer mealId);
    Meal get(Integer userId, Integer mealId);
    List<Meal> getAll(Integer userId);
    List<Meal> getFilteredByDate(Integer userId, LocalDate startTime, LocalDate endTime);

}
