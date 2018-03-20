package ru.dsoccer1980.dishvote.repository;


import ru.dsoccer1980.dishvote.model.User;


public interface UserRepository {

    User get(int id);

    void save(User user);
}
