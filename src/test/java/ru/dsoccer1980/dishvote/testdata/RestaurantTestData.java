package ru.dsoccer1980.dishvote.testdata;

import ru.dsoccer1980.dishvote.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.dishvote.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int RESTAURANT_ID1 = START_SEQ + 3;
    public static final int RESTAURANT_ID2 = START_SEQ + 4;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID1, "Ginza", "Sadovaya 12");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID2, "Teremok", "Nevskij 10");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
