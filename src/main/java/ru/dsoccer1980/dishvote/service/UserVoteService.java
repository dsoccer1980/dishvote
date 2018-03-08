package ru.dsoccer1980.dishvote.service;


import ru.dsoccer1980.dishvote.model.UserVote;

import java.time.LocalDate;
import java.util.List;


public interface UserVoteService {

    UserVote save(Integer userId, Integer restaurantId, LocalDate date);

    List<UserVote> getAllVotesForUser(int userId);

}