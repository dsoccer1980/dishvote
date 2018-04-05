package ru.dsoccer1980.dishvote.util;

import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.to.UserTo;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class UserUtils {

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static int getId(String id) {
        return Integer.parseInt(id);
    }

    public static User getUser(HttpServletRequest request, String action) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean enabled = Boolean.valueOf(request.getParameter("enabled"));
        boolean isAdmin = Boolean.valueOf(request.getParameter("is_admin"));
        LocalDate registered = LocalDate.now();
        Integer id = null;

        if (action.equals("editUser")) {
            registered = LocalDate.parse(request.getParameter("registered"));
            id = getId(request.getParameter("id"));
        }
        User user = new User(id, name, email, password, registered, enabled, isAdmin);
        return user;
    }
}
