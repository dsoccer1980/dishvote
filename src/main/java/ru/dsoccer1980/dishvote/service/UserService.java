package ru.dsoccer1980.dishvote.service;


import ru.dsoccer1980.dishvote.model.User;


public interface UserService {

    User get(int id);

    void update(User user);
}