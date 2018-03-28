package ru.dsoccer1980.dishvote.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.service.VoteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Controller
public class AdminVoteRestController {
    private static final Logger log = LoggerFactory.getLogger(AdminVoteRestController.class);

    private final VoteService service;

    @Autowired
    public AdminVoteRestController(VoteService service) {
        this.service = service;
    }

    public Map<Restaurant, Long> getVotesForRestaurantOnDate(LocalDate date) {
        log.info("get all votes for date:  {}", date);
        return service.getVotesForRestaurantOnDate(date);
    }

    public List<User> getVotesOfUsersByRestaurantAndDate(int restaurantId, LocalDate date) {
        log.info("get votes of users by restaurant: {} and date: {}", restaurantId, date);
        return service.getUsersVotedByRestaurantAndDate(restaurantId, date);
    }
}