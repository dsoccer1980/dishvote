package ru.dsoccer1980.dishvote.repository.mock;

import org.springframework.stereotype.Repository;
import ru.dsoccer1980.dishvote.UserTestData;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.dsoccer1980.dishvote.UserTestData.ADMIN;
import static ru.dsoccer1980.dishvote.UserTestData.USER;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    public void init() {
        repository.clear();
        repository.put(UserTestData.USER_ID, USER);
        repository.put(UserTestData.ADMIN_ID, ADMIN);
    }


    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public void save(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
