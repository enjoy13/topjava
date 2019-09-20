package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).forEach(System.out::println);

        getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).forEach(System.out::println);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<Integer, Integer> mapDayCalories = new HashMap<>();

        mealList.forEach(userMeal ->
                mapDayCalories.merge(userMeal.getDateTime().getDayOfYear(), userMeal.getCalories(), Integer::sum)
        );

        List<UserMealWithExceed> listWithFilters = new ArrayList<>();

        mealList.forEach(userMeal -> {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                listWithFilters.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                        mapDayCalories.get(userMeal.getDateTime().getDayOfYear()) > caloriesPerDay));
        });

        return listWithFilters;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<Integer, Integer> tempMap = mealList.stream()
                .collect(Collectors.groupingBy(x -> x.getDateTime().getDayOfYear(), Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(y -> TimeUtil.isBetween(y.getDateTime().toLocalTime(), startTime, endTime))
                .map(x -> new UserMealWithExceed(x.getDateTime(), x.getDescription(), x.getCalories(), tempMap.get(x.getDateTime().getDayOfYear()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

}
