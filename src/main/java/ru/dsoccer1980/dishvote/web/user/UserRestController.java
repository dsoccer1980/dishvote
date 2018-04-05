package ru.dsoccer1980.dishvote.web.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.service.UserService;
import ru.dsoccer1980.dishvote.web.AuthorizedUser;


import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = UserRestController.REST_URL)
public class UserRestController {

    static final String REST_URL = "/rest/user/";

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public String getUser(Model model, HttpServletRequest request) {
        int id = AuthorizedUser.getId();
        model.addAttribute("user", userService.get(id));
        return "userProfile";
    }

    @PostMapping("/update")
    public String updateUser(Model model, HttpServletRequest request) {
        int id = AuthorizedUser.getId();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(id, name, email, password, userService.get(id).getRegistered(), true, false);
        userService.update(user);
        model.addAttribute("user", user);
        return "mainMenuUser";
    }
}