package ru.dsoccer1980.dishvote.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.dsoccer1980.dishvote.model.UserVote;
import ru.dsoccer1980.dishvote.service.UserVoteService;
import ru.dsoccer1980.dishvote.util.VoteException;

import java.time.LocalDate;
import java.util.List;


@Controller
public class UserVoteRestController {
    private static final Logger log = LoggerFactory.getLogger(UserVoteRestController.class);

    private final UserVoteService service;

    @Autowired
    public UserVoteRestController(UserVoteService service) {
        this.service = service;
    }

    public UserVote save(Integer userId, Integer restaurantId, LocalDate date) {
        UserVote userVote = service.save(userId, restaurantId, date);
        if (userVote == null) throw new VoteException("You can not vote this day more");
        return userVote;
    }

    public List<UserVote> getAllVotesForUser(int userId) {
        return service.getAllVotesForUser(userId);
    }



}