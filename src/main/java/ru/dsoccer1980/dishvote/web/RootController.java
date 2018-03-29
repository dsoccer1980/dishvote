package ru.dsoccer1980.dishvote.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.service.UserService;
import ru.dsoccer1980.dishvote.web.user.UserRestController;


import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRestController userController;

    @GetMapping("/")
    public String root() {
        return "loginPage";
    }

    @PostMapping("/menu")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        User user = userController.get(userId);
        request.setAttribute("user", user);
        if (user.isAdmin()) {
            return "mainMenuAdmin";
            //request.getRequestDispatcher("/mainMenuAdmin.jsp").forward(request, response);
        }
        else {
            return "mainMenuUser";
           // request.getRequestDispatcher("/mainMenuUser.jsp").forward(request, response);
        }
    }

}


//<servlet-mapping>
//<servlet-name>restaurantServlet</servlet-name>
//<url-pattern>/restaurant</url-pattern>
//</servlet-mapping>
//<servlet-mapping>
//<servlet-name>userVoteServlet</servlet-name>
//<url-pattern>/userVote</url-pattern>
//</servlet-mapping>
//<servlet-mapping>
//<servlet-name>adminVoteServlet</servlet-name>
//<url-pattern>/adminVote</url-pattern>
//</servlet-mapping>
//<servlet-mapping>
//<servlet-name>menuServlet</servlet-name>
//<url-pattern>/menu</url-pattern>
//</servlet-mapping>
//<servlet-mapping>
//<servlet-name>dishServlet</servlet-name>
//<url-pattern>/dish</url-pattern>
//</servlet-mapping>
//<servlet-mapping>
//<servlet-name>userServlet</servlet-name>
//<url-pattern>/user</url-pattern>
//</servlet-mapping>
//<servlet-mapping>
//<servlet-name>adminServlet</servlet-name>
//<url-pattern>/admin</url-pattern>
//</servlet-mapping>
//<servlet-mapping>
//<servlet-name>mainPageServlet</servlet-name>
//<url-pattern>/</url-pattern>
//</servlet-mapping>
