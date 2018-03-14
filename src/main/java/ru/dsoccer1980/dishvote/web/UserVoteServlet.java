package ru.dsoccer1980.dishvote.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dsoccer1980.dishvote.util.VoteException;
import ru.dsoccer1980.dishvote.web.dish.DishRestController;
import ru.dsoccer1980.dishvote.web.vote.UserVoteRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

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
        int userId = AuthorizedUser.getId();

        if ("chosenDate".equals(action)) {
            LocalDate date = LocalDate.parse(request.getParameter("date"));

            request.setAttribute("date", date);
            request.setAttribute("dishes", dishController.getAll());
            request.setAttribute("allVotesForUser", userVoteController.getAllVotesForUser(userId));

            request.getRequestDispatcher("/voteForm.jsp").forward(request, response);
        }
        else {

            LocalDate date = LocalDate.parse(request.getParameter("date"));
            int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
            try {
                userVoteController.save(userId, restaurantId, date);
            } catch (VoteException e) {
                request.setAttribute("message", e.getMessage());
            }
            request.setAttribute("dishes", dishController.getAll());
            request.setAttribute("allVotesForUser", userVoteController.getAllVotesForUser(userId));
            request.getRequestDispatcher("/voteForm.jsp").forward(request, response);
        }
    }


}
