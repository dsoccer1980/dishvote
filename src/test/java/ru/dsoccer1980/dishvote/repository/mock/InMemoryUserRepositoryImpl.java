package ru.dsoccer1980.dishvote.repository.mock;

import org.springframework.stereotype.Repository;
import ru.dsoccer1980.dishvote.testdata.UserTestData;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.dsoccer1980.dishvote.testdata.UserTestData.ADMIN;
import static ru.dsoccer1980.dishvote.testdata.UserTestData.USER1;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    public void init() {
        repository.clear();
        repository.put(UserTestData.USER_ID1, USER1);
        repository.put(UserTestData.ADMIN_ID, ADMIN);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(repository.values());
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }
}
