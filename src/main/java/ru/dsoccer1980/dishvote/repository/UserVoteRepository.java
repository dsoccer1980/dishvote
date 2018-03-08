package ru.dsoccer1980.dishvote.repository;


import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.UserVote;

import java.time.LocalDate;


public interface UserVoteRepository {

    UserVote save(User user, Restaurant restaurant, LocalDate date);


}
