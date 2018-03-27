package ru.dsoccer1980.dishvote.repository.mock;

import org.springframework.stereotype.Repository;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.UserVote;
import ru.dsoccer1980.dishvote.repository.VoteRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.dsoccer1980.dishvote.testdata.UserTestData.USER_ID1;
import static ru.dsoccer1980.dishvote.testdata.UserVoteTestData.*;


@Repository
public class InMemoryUserVoteRepositoryImpl implements VoteRepository {

    //   map( userId -> (userVoteId, UserVote))
    private Map<Integer, Map<Integer, UserVote>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    public void init() {
        repository.clear();
        Map<Integer, UserVote> userVotes = new ConcurrentHashMap<>();
        userVotes.put(USER_VOTE_ID1, USER_VOTE1);
        repository.put(USER_ID1, userVotes);
    }

    @Override
    public UserVote save(User user, Restaurant restaurant, LocalDate date) {
        UserVote userVote = new UserVote(null, user, restaurant, date);
        userVote.setId(counter.incrementAndGet());
        Map<Integer, UserVote> map = new ConcurrentHashMap<>();
        if (repository.containsKey(user.getId())) {
            map = repository.get(user.getId());
            map.put(userVote.getId(), userVote);
        }
        else {
            map.put(userVote.getId(), userVote);
            repository.put(userVote.getUser().getId(), map);
        }
        return userVote;
    }

    @Override
    public List<UserVote> getAllVotesForUser(User user) {
        return new ArrayList<>(repository.get(user.getId()).values());
    }

    @Override
    public UserVote getUserVote(User user, Restaurant restaurant, LocalDate date) {
        return repository.get(user.getId()).values().stream()
                .filter(userVote -> (userVote.getRestaurant().equals(restaurant) && userVote.getDate().equals(date)))
                .findFirst().orElse(null);
    }


    @Override
    public List<Object[]> getVotesForDate(LocalDate date) {
        return null;
    }

    @Override
    public List<User> getUsersVotedByRestaurantAndDate(int restaurantId, LocalDate date) {
        List<Map<Integer, UserVote>> list = repository.values().stream().collect(Collectors.toList());
        List<User> result = new ArrayList<>();
        for (Map<Integer, UserVote> map : list) {
            result.add(map.values().stream().filter(userVote -> (userVote.getRestaurant().getId() == restaurantId && userVote.getDate().equals(date)))
                    .map(UserVote::getUser).findFirst().orElse(null));
        }
        return result;
    }
}
