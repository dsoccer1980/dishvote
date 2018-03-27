package ru.dsoccer1980.dishvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.UserVote;
import ru.dsoccer1980.dishvote.repository.RestaurantRepository;
import ru.dsoccer1980.dishvote.repository.UserRepository;
import ru.dsoccer1980.dishvote.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class VoteServiceImpl implements VoteService {

    private final LocalTime DEADLINE = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository,
                           UserRepository userRepository,
                           RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public UserVote save(Integer userId, Integer restaurantId, LocalDate date) {
        if (canVote(date)) {
            return voteRepository.save(userRepository.get(userId), restaurantRepository.get(restaurantId), date);
        }
        else {
            return null;
        }
    }

    @Override
    public List<UserVote> getAllVotesForUser(int userId) {
        User user = userRepository.get(userId);
        return voteRepository.getAllVotesForUser(user);
    }

    @Override
    public Map<Restaurant, Long> getAllVotesForDate(LocalDate date) {
        List<Object[]> votesForDate = voteRepository.getVotesForDate(date);
        Map<Restaurant, Long> result = new LinkedHashMap<>();
        for (Object[] vote : votesForDate) {
            result.put(restaurantRepository.get((Integer)vote[1]), Long.parseLong(vote[0].toString()));
        }

        return result;
    }

    @Override
    public List<User> getUsersVotedByRestaurantAndDate(int restaurantId, LocalDate date) {
        return voteRepository.getUsersVotedByRestaurantAndDate(restaurantId, date);
    }

    private boolean canVote(LocalDate date) {
        return LocalDateTime.now().isBefore(LocalDateTime.of(date, DEADLINE));
    }
}