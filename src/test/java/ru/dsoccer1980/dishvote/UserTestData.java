package ru.dsoccer1980.dishvote;

import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.dishvote.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_ID1 = START_SEQ;
    public static final int USER_ID2 = START_SEQ + 1;
    public static final int ADMIN_ID = START_SEQ + 2;

    public static final User USER1 = new User(USER_ID1, "User1", "user1@yandex.ru", "password", LocalDate.now(), true, false);
    public static final User USER2 = new User(USER_ID2, "User2", "user2@yandex.ru", "password", LocalDate.now(), true, false);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", LocalDate.now(), true, true);

    public static final int RESTAURANT_ID1 = START_SEQ + 3;
    public static final int RESTAURANT_ID2 = START_SEQ + 4;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID1, "Ginza", "Sadovaya 12");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID2, "Teremok", "Nevskij 10");

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered").isEqualTo(expected);
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatchRestaurant(actual, Arrays.asList(expected));
    }

    public static void assertMatchRestaurant(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
