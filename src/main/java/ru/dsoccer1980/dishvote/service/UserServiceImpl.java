package ru.dsoccer1980.dishvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void create(User user) {
        repository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }
}