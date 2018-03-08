package ru.dsoccer1980.dishvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.dishvote.model.UserVote;
import ru.dsoccer1980.dishvote.repository.RestaurantRepository;
import ru.dsoccer1980.dishvote.repository.UserRepository;
import ru.dsoccer1980.dishvote.repository.UserVoteRepository;

import java.time.LocalDate;


@Service
public class UserVoteServiceImpl implements UserVoteService {

    private final UserVoteRepository userVoteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public UserVoteServiceImpl(UserVoteRepository userVoteRepository,
                               UserRepository userRepository,
                               RestaurantRepository restaurantRepository) {
        this.userVoteRepository = userVoteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public UserVote save(Integer userId, Integer restaurantId, LocalDate date) {
        return userVoteRepository.save(userRepository.get(userId), restaurantRepository.get(restaurantId), date);
    }
}