package ru.dsoccer1980.dishvote.repository;


import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.UserVote;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface VoteRepository {

    UserVote save(User user, Restaurant restaurant, LocalDate date);

    List<UserVote> getAllVotesForUser(User user);

    UserVote getUserVote(User user, Restaurant restaurant, LocalDate date);

    Map<Integer, Long> getVotesForRestaurantOnDate(LocalDate date);

    List<User> getUsersVotedByRestaurantAndDate(int restaurantId, LocalDate date);
}
