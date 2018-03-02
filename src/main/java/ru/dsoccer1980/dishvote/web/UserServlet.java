package ru.dsoccer1980.dishvote.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet { //extends HttpServlet {
//    private static final Logger log = getLogger(UserServlet.class);
//
//    private ConfigurableApplicationContext springContext;
//    private MealRestController mealController;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
//        mealController = springContext.getBean(MealRestController.class);
//    }
//
//    @Override
//    public void destroy() {
//        springContext.close();
//        super.destroy();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int userId = Integer.parseInt(request.getParameter("userId"));
//        request.setAttribute("meals", mealController.get());
//        request.getRequestDispatcher("/meals.jsp").forward(request, response);
//        //response.sendRedirect("meals");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        log.debug("forward to users");
//        request.getRequestDispatcher("/users.jsp").forward(request, response);
//    }
}
