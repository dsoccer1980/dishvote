package ru.dsoccer1980.dishvote.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dsoccer1980.dishvote.model.User;;
import ru.dsoccer1980.dishvote.service.UserService;
import javax.servlet.http.HttpServletRequest;


@Controller
public class RootController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root(HttpServletRequest request) {
        User user = userService.get(AuthorizedUser.getId());
        request.setAttribute("user", user);
        if (user.isAdmin()) {
            return "mainMenuAdmin";
        }
        else {
            return "mainMenuUser";
        }
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "loginPage";
    }

}
