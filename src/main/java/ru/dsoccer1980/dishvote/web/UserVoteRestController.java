package ru.dsoccer1980.dishvote.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.dsoccer1980.dishvote.model.UserVote;
import ru.dsoccer1980.dishvote.service.UserVoteService;


@Controller
public class UserVoteRestController {
    private static final Logger log = LoggerFactory.getLogger(UserVoteRestController.class);

    private final UserVoteService service;

    @Autowired
    public UserVoteRestController(UserVoteService service) {
        this.service = service;
    }

    public UserVote save(Integer userId, Integer restaurantId) {
        return service.save(userId, restaurantId);
    }



}