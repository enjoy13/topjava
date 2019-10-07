package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {

    List<Meal> findAll();

    void addl(Meal meal);

    void delete(int mealId);

    void update(Meal meal);

    Meal getById(int mealId);
}
