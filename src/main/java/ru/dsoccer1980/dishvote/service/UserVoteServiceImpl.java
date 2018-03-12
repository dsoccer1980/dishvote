package ru.dsoccer1980.dishvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.UserVote;
import ru.dsoccer1980.dishvote.repository.RestaurantRepository;
import ru.dsoccer1980.dishvote.repository.UserRepository;
import ru.dsoccer1980.dishvote.repository.UserVoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Service
public class UserVoteServiceImpl implements UserVoteService {

    private final LocalTime DEADLINE = LocalTime.of(11, 0);

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
        if (canVote(date)) {
            return userVoteRepository.save(userRepository.get(userId), restaurantRepository.get(restaurantId), date);
        }
        else {
            return null;
        }
    }

    @Override
    public List<UserVote> getAllVotesForUser(int userId) {
        User user = userRepository.get(userId);
        return userVoteRepository.getAllVotesForUser(user);
    }

    private boolean canVote(LocalDate date) {
        return LocalDateTime.now().isBefore(LocalDateTime.of(date, DEADLINE));
    }
}