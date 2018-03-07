package ru.dsoccer1980.dishvote.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dsoccer1980.dishvote.web.dish.DishRestController;
import ru.dsoccer1980.dishvote.web.vote.UserVoteRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserVoteServlet extends HttpServlet {
    private static final Logger log = getLogger(UserVoteServlet.class);

    private ConfigurableApplicationContext springContext;
    private UserVoteRestController userVoteController;
    private DishRestController dishController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        userVoteController = springContext.getBean(UserVoteRestController.class);
        dishController = springContext.getBean(DishRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("chosenDate".equals(action)) {
            request.setAttribute("dishes", dishController.getAll());
            request.getRequestDispatcher("/voteForm.jsp").forward(request, response);
        }
        else {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
            userVoteController.save(userId, restaurantId);
        }
    }


}
