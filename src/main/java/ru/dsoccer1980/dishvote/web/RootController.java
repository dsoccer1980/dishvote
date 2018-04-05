package ru.dsoccer1980.dishvote.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dsoccer1980.dishvote.model.User;;
import ru.dsoccer1980.dishvote.service.UserService;
import javax.servlet.http.HttpServletRequest;


@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {
        return "loginPage";
    }

    @PostMapping("/menu")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        User user = userService.get(userId);
        request.setAttribute("user", user);
        if (user.isAdmin()) {
            return "mainMenuAdmin";
        }
        else {
            return "mainMenuUser";
        }
    }

}
