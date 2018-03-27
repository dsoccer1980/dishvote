package ru.dsoccer1980.dishvote.repository.mock;

import org.springframework.stereotype.Repository;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.UserVote;
import ru.dsoccer1980.dishvote.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class InMemoryUserVoteRepositoryImpl implements VoteRepository {

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    public void init() {
        repository.clear();

    }

    @Override
    public UserVote save(User user, Restaurant restaurant, LocalDate date) {
        return null;
    }

    @Override
    public List<UserVote> getAllVotesForUser(User user) {
        return null;
    }

    @Override
    public UserVote getUserVote(User user, Restaurant restaurant, LocalDate date) {
        return null;
    }

    @Override
    public boolean isNew(User user) {
        return false;
    }

    @Override
    public List<Object[]> getVotesForDate(LocalDate date) {
        return null;
    }

    @Override
    public List<User> getVotesOfUsersByRestaurantAndDate(int restaurantId, LocalDate date) {
        return null;
    }
}
