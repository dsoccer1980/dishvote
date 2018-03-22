package ru.dsoccer1980.dishvote.service;


import ru.dsoccer1980.dishvote.model.User;

import java.util.List;


public interface UserService {

    User get(int id);

    void update(User user);

    List<User> getAll();

    void create(User user);

    boolean delete(int id);
}