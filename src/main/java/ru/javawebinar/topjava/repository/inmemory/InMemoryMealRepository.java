package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal save(Integer userId, Meal meal) {
        log.info("save method, userId {}, meal {}", userId, meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.computeIfAbsent(userId, mapMeal -> new ConcurrentHashMap<>()).put(meal.getId(), meal);
        }
        return repository.getOrDefault(userId, null).computeIfPresent(meal.getId(), (key, value) -> meal);
    }

    @Override
    public boolean delete(Integer userId, Integer mealId) {
        log.info("delete method, userId {}, mealId {}", userId, mealId);
        return repository.get(userId).remove(mealId) != null;
    }

    @Override
    public Meal get(Integer userId, Integer mealId) {
        log.info("get method {} {}", userId, mealId);
        return repository.get(userId).getOrDefault(mealId, null);
    }

    @Override
    public List<Meal> getAll(Integer userId) {
        log.info("getAll method, userId {}", userId);
        return getSortByDate(userId, x -> true);
    }

    private List<Meal> getSortByDate(Integer userId, Predicate<Meal> filter) {
        log.info("getSortByDate method, userId {}", userId);
        return repository.get(userId) == null ? Collections.emptyList() :
                repository.get(userId)
                .values()
                .stream()
                .filter(filter.and(meal -> meal.getUserId() == userId))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getFilteredByDate(Integer userId, LocalDate startDate, LocalDate endDate) {
        log.info("getFilteredByDate method, userId {}, startDate {}, endDate {}", userId, startDate, endDate);
        return getSortByDate(userId, meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate));

    }
}
