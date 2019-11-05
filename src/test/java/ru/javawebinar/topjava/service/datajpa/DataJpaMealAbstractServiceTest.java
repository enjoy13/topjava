package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.AbstractMealAbstractServiceTest;
import ru.javawebinar.topjava.service.MealService;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealAbstractServiceTest extends AbstractMealAbstractServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    public void getMealByIdAndUserId() throws Exception {
        Meal actual = mealService.getMealByIdAndUserId(ADMIN_MEAL_ID, ADMIN_ID);
        assertThat(actual).isEqualTo(ADMIN_MEAL_WITH_USER);
    }
}
