package ru.dsoccer1980.dishvote.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.web.vote.AdminVoteRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class AdminVoteServlet extends HttpServlet {
    private static final Logger log = getLogger(AdminVoteServlet.class);

    private ConfigurableApplicationContext springContext;
    private AdminVoteRestController voteController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        voteController = springContext.getBean(AdminVoteRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate date = LocalDate.now();
        String dateString = request.getParameter("date");
        if (dateString != null) {
            date = LocalDate.parse(request.getParameter("date"));
        }
        request.setAttribute("date", date);
        Map<Restaurant, Long> allVotesForDate = voteController.getAllVotesForDate(date);
        if (allVotesForDate.size() == 0) {
            request.setAttribute("message", "There are not votes on this date");
        }
        else {
            request.setAttribute("allVotesForDate", allVotesForDate);
        }
        request.getRequestDispatcher("/voteForDateForm.jsp").forward(request, response);
    }
}
