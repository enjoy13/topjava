package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserAbstractServiceTest;
import ru.javawebinar.topjava.service.UserService;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static ru.javawebinar.topjava.UserTestData.ADMIN_BY_MEALS;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserAbstractServiceTest extends AbstractUserAbstractServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserById() throws Exception {
        User user = userService.getUserById(ADMIN_ID);
        assertThat(user).isEqualTo(ADMIN_BY_MEALS);
    }
}
