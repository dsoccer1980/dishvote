package ru.dsoccer1980.dishvote.repository;

import ru.dsoccer1980.dishvote.model.User;

public interface MealRepository {

    // null if meal do not belong to userId
    User get();


}
