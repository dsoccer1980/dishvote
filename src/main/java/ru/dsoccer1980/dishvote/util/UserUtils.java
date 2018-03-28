package ru.dsoccer1980.dishvote.util;

import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.to.UserTo;

public class UserUtils {

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
