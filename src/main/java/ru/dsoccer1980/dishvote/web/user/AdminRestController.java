package ru.dsoccer1980.dishvote.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dsoccer1980.dishvote.service.UserService;
import ru.dsoccer1980.dishvote.util.UserUtils;
import ru.dsoccer1980.dishvote.web.AuthorizedUser;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping(value = AdminRestController.REST_URL)
public class AdminRestController {

    static final String REST_URL = "/rest/admin/";

    @Autowired
    private UserService userService;

    @GetMapping("/showUsers")
    public String adminShowUsers(HttpServletRequest request) {
        int userId = AuthorizedUser.getId();

        request.setAttribute("users", userService.getAll());
        return "userList";
    }

    @GetMapping("/userEditForm/id/{id}")
    public String adminUserEditForm(@PathVariable(value = "id") int id, HttpServletRequest request) {
        request.setAttribute("user", userService.get(id));
        return "userEditForm";
    }

    @GetMapping("/userDelete/id/{id}")
    public String adminUserDelete(@PathVariable(value = "id") int id, HttpServletRequest request) {
        userService.delete(id);

        request.setAttribute("users", userService.getAll());
        return "userList";
    }

    @PostMapping("/addUser")
    public String adminAddUser(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        userService.update(UserUtils.getUser(request, "addUser"));

        request.setAttribute("users", userService.getAll());
        return "userList";
    }

    @PostMapping("/editUser")
    public String adminEditUser(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        userService.create(UserUtils.getUser(request, "editUser"));

        request.setAttribute("users", userService.getAll());
        return "userList";
    }
}