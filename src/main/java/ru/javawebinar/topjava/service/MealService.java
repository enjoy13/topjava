package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealRepository repository;

    public Meal create(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    public void delete(int userId, int mealId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(userId, mealId), mealId);
    }

    public Meal get(int userId, int mealId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(userId, mealId), mealId);
    }

    public List<MealTo> getAll(int userId, int caloriesPerDay) {
        return MealsUtil.getTos(repository.getAll(userId), caloriesPerDay);
    }

    public List<MealTo> getByDateTime(LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate, int userId, int caloriesPerDay) {
        log.info("getByDateTime {} {} {} {} {} {}", startTime, endTime, startDate, endDate, userId, caloriesPerDay);
        return MealsUtil.getFilteredTos(repository.getFilteredByDate(userId, startDate, endDate), caloriesPerDay, startTime, endTime);
    }

    public void update(int userId, Meal meal) throws NotFoundException {
        checkNotFoundWithId(repository.save(userId, meal), meal.getId());
    }

}