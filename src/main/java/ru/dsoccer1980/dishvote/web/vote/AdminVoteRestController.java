package ru.dsoccer1980.dishvote.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.service.VoteService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = AdminVoteRestController.REST_URL)
public class AdminVoteRestController {

    static final String REST_URL = "/rest/adminVote/";

    @Autowired
    private VoteService voteService;

    @GetMapping("/show")
    public String adminVoteShow(HttpServletRequest request) {
        LocalDate date = LocalDate.now();
        String dateString = request.getParameter("date");
        if (dateString != null) {
            date = LocalDate.parse(request.getParameter("date"));
        }
        request.setAttribute("date", date);
        Map<Restaurant, Long> allVotesForDate = voteService.getVotesForRestaurantOnDate(date);
        if (allVotesForDate.size() == 0) {
            request.setAttribute("message", "There are not votes on this date");
        } else {
            request.setAttribute("allVotesForDate", allVotesForDate);
        }
        return "voteForDateForm";
    }

    @GetMapping("/showUsersByRestaurantAndDate/restaurant_id/{restaurant_id}/date/{date}")
    public String adminVoteShowUsersByRestaurantAndDate(@PathVariable(value = "restaurant_id") String restaurant_id,
                                                        @PathVariable(value = "date") String date,
                                                        HttpServletRequest request)
    {
        LocalDate localDate = LocalDate.parse(date);
        int restaurantId = Integer.parseInt(restaurant_id);
        List<User> userList = voteService.getUsersVotedByRestaurantAndDate(restaurantId, localDate);
        request.setAttribute("userList", userList);
        return "voteUsersByRestaurantAndDate";
    }
}