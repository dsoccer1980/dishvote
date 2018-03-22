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
        return service.get(id);
    }

    public void update(User user) {
        service.update(user);
    }

    public List<User> getAll() {
        return service.getAll();
    }

    public void create(User user) {
        service.create(user);
    }
}