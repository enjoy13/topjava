package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealRepositoryImpl implements MealRepository {

    private ConcurrentHashMap<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    private static AtomicInteger lastId = new AtomicInteger();


    @Override
    public List<Meal> findAllMeal() {
        return new ArrayList<>(mealMap.values());
    }

    @Override
    public void addMeal(Meal meal) {
        meal.setId(lastId.incrementAndGet());
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public void deleteMeal(int mealId) {
        mealMap.remove(mealId);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealMap.replace(meal.getId(), meal);
    }

    @Override
    public Meal getMealById(int mealId) {
        return mealMap.get(mealId);
    }
}
