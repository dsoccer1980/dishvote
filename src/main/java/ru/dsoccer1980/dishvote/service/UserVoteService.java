package ru.dsoccer1980.dishvote.service;


import ru.dsoccer1980.dishvote.model.UserVote;


public interface UserVoteService {

    UserVote save(Integer userId, Integer restaurantId);

}