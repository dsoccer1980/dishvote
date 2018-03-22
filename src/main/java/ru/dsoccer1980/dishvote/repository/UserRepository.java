package ru.dsoccer1980.dishvote.repository;


import ru.dsoccer1980.dishvote.model.User;

import java.util.List;


public interface UserRepository {

    User get(int id);

    void save(User user);

    List<User> getAll();
}
