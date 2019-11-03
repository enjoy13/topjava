package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.DateTimeUtil.getEndExclusive;
import static ru.javawebinar.topjava.util.DateTimeUtil.getStartInclusive;

@Repository
public class DataJpaMealRepository implements MealRepository {
    private static final Sort SORT_BY_TIME = Sort.by(Sort.Direction.DESC,  "dateTime");

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Meal save(Meal meal, int userId) {
        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }
        meal.setUser(crudUserRepository.getOne(userId));
        crudMealRepository.save(meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        var meal = get(id, userId);
        if (meal != null) {
            crudMealRepository.delete(meal);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        var meal = crudMealRepository.findById(id);
        if (meal.isPresent()) {
            if (meal.get().getUser().getId() == userId)
                return meal.get();
            else
                return null;
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudMealRepository.findAll(SORT_BY_TIME)
                .stream().filter(meal -> meal.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetweenInclusive(LocalDate startDate, LocalDate endDate, int userId) {
        return crudMealRepository.getBetweenInclusive(getStartInclusive(startDate), getEndExclusive(endDate), userId);
    }
}
