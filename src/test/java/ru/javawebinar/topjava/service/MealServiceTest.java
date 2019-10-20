package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Test
    public void get() {
        Meal actualMeal = mealService.get(MEAL_ID, USER_ID);
        assertThat(actualMeal).isEqualToComparingFieldByField(MEAL_TEST_FIRST);
    }

    @Test(expected = NotFoundException.class)
    public void getWithUnknowId() {
        mealService.get(1, USER_ID);
    }

    @Test
    public void delete() {
        mealService.delete(MEAL_ID, USER_ID);
        assertThat(mealService.getAll(USER_ID)).usingFieldByFieldElementComparator().isEqualTo(Collections.singletonList(MEAL_TEST_SECOND));
    }

    @Test(expected = NotFoundException.class)
    public void deleteWithUnknowId() {
        mealService.delete(1, USER_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> mealList = mealService.getBetweenDates(START_DATE_TEST, END_DATE_TEST, USER_ID);
        assertThat(mealList).usingFieldByFieldElementComparator().isEqualTo(Collections.singletonList(MEAL_TEST_FIRST));
    }

    @Test
    public void getAll() {
        assertThat(mealService.getAll(USER_ID)).usingFieldByFieldElementComparator().isEqualTo(Arrays.asList(MEAL_TEST_FIRST, MEAL_TEST_SECOND));
    }

    @Test
    public void getAllWithAnotherId() {
        assertThat(mealService.getAll(ADMIN_ID)).usingFieldByFieldElementComparator().isNotEqualTo(Arrays.asList(MEAL_TEST_FIRST, MEAL_TEST_SECOND));
    }

    @Test
    public void update() {
        Meal updaterMeal = new Meal(MEAL_TEST_FIRST);
        updaterMeal.setDescription("UpdaterMeal");
        updaterMeal.setCalories(200);
        mealService.update(updaterMeal, USER_ID);
        assertThat(mealService.get(MEAL_ID, USER_ID)).isEqualToComparingFieldByField(updaterMeal);
    }

    @Test(expected = NotFoundException.class)
    public void updateWithUnknowId() {
        Meal updaterMeal = new Meal(MEAL_TEST_FIRST);
        updaterMeal.setDescription("UpdaterMeal");
        updaterMeal.setCalories(200);
        mealService.update(updaterMeal, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal actualMeal = new Meal(DateTimeUtil.parseLocalDateTime("2019-10-19 09:00"), "testMeal", 1000);
        Meal newMeal = mealService.create(actualMeal, USER_ID);
        assertThat(mealService.getAll(USER_ID)).usingFieldByFieldElementComparator().isEqualTo(Arrays.asList(MEAL_TEST_FIRST, newMeal, MEAL_TEST_SECOND));
    }

    @Test
    public void createWithAnotherId() {
        Meal actualMeal = new Meal(DateTimeUtil.parseLocalDateTime("2019-10-19 09:00"), "testMeal", 1000);
        Meal newMeal = mealService.create(actualMeal, ADMIN_ID);
        assertThat(mealService.getAll(ADMIN_ID)).usingFieldByFieldElementComparator().isNotEqualTo(Arrays.asList(MEAL_TEST_FIRST, newMeal, MEAL_TEST_SECOND));
    }
}
