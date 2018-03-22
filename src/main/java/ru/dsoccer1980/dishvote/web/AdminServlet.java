package ru.dsoccer1980.dishvote.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.web.user.UserRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;


public class AdminServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private UserRestController userController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        userController = springContext.getBean(UserRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean enabled = Boolean.valueOf(request.getParameter("enabled"));
        boolean isAdmin = Boolean.valueOf(request.getParameter("is_admin"));
        LocalDate registered = LocalDate.now();
        Integer id = null;
        if (action.equals("editUser")) {
            registered = LocalDate.parse(request.getParameter("registered"));
            id = getId(request);
        }
        User user = new User(id, name, email, password, registered, enabled, isAdmin);

        if (action.equals("addUser")) {
            userController.update(user);
        }
        else if (action.equals("editUser")) {
            userController.create(user);
        }
        request.setAttribute("users", userController.getAll());
        request.getRequestDispatcher("/userList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = AuthorizedUser.getId();

        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("users", userController.getAll());
            request.getRequestDispatcher("/userList.jsp").forward(request, response);
        }
        else if (action.equals("userEdit")) {
            request.setAttribute("user", userController.get(getId(request)));
            request.getRequestDispatcher("/userEditForm.jsp").forward(request, response);
        }
        else if (action.equals("userDelete")) {
            int id = getId(request);
            userController.delete(id);

            request.setAttribute("users", userController.getAll());
            request.getRequestDispatcher("/userList.jsp").forward(request, response);
        }
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

}
