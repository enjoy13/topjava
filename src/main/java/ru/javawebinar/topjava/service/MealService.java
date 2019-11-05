package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public Meal get(int id, int userId) {
        return checkNotFoundWithId(mealRepository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(mealRepository.delete(id, userId), id);
    }

    public List<Meal> getBetweenDates(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return mealRepository.getBetweenInclusive(startDate, endDate, userId);
    }

    @Cacheable("meals")
    public List<Meal> getAll(int userId) {
        return mealRepository.getAll(userId);
    }

    public void update(Meal meal, int userId) {
        Assert.notNull(meal, "meal must not be null");
        checkNotFoundWithId(mealRepository.save(meal, userId), meal.getId());
    }

    public Meal create(Meal meal, int userId) {
        Assert.notNull(meal, "meal must not be null");
        return mealRepository.save(meal, userId);
    }

    public Meal getMealByIdAndUserId(int id, int userId) {
        return checkNotFoundWithId(mealRepository.getWithUser(id, userId), id);
    }
}