package ru.javawebinar.topjava.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Controller
public class AdminRestController extends AbstractUserController {
    private static final Logger log = LoggerFactory.getLogger(AdminRestController.class);

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    public User getByMail(String email) {
        return super.getByMail(email);
    }
}