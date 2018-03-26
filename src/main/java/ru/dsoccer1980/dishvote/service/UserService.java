package ru.dsoccer1980.dishvote.service;


import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;

import java.util.List;


public interface UserService {

    User get(int id) throws NotFoundException;

    void update(User user)  throws NotFoundException;

    List<User> getAll();

    User create(User user);

    void delete(int id) throws NotFoundException;
}