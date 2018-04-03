package ru.dsoccer1980.dishvote.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.service.UserService;
import ru.dsoccer1980.dishvote.web.AuthorizedUser;

import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserRestController {
    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(Model model) {
        int id = AuthorizedUser.getId();
        log.info("get user:  {}", id);
        model.addAttribute("user", service.get(id));
        return "userProfile";
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

    public void delete(int id) {
        log.info("delete user:  {}", id);
        service.delete(id);
    }
}