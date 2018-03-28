package ru.dsoccer1980.dishvote.service;


import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.UserVote;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface VoteService {

    UserVote save(Integer userId, Integer restaurantId, LocalDate date);

    List<UserVote> getAllVotesForUser(int userId);

    Map<Restaurant, Long> getVotesForRestaurantOnDate(LocalDate date);

    List<User> getUsersVotedByRestaurantAndDate(int restaurantId, LocalDate date);

}