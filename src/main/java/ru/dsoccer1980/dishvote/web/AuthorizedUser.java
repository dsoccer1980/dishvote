package ru.dsoccer1980.dishvote.web;

import ru.dsoccer1980.dishvote.model.AbstractBaseEntity;

public class AuthorizedUser {
    private static int id; //= AbstractBaseEntity.START_SEQ;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

}
