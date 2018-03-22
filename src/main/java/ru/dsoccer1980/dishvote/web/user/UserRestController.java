package ru.dsoccer1980.dishvote.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.service.UserService;

import java.util.List;


@Controller
public class UserRestController {
    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    public User get(int id) {
        log.info("get user:  {}", id);
        return service.get(id);
    }

    public void update(User user) {
        log.info("update user:  {}", user);
        service.update(user);
    }

    public List<User> getAll() {
        log.info("get all users:");
        return service.getAll();
    }

    public void create(User user) {
        log.info("create user:  {}", user);
        service.create(user);
    }

    public boolean delete(int id) {
        log.info("delete user:  {}", id);
        return service.delete(id);
    }
}