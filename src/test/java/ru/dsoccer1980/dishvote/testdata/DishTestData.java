package ru.dsoccer1980.dishvote.testdata;

import ru.dsoccer1980.dishvote.model.Dish;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.dishvote.model.AbstractBaseEntity.START_SEQ;
import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT1;
import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT2;

public class DishTestData {
    
    public static final LocalDate TEST_DATE = LocalDate.of(2018, 03, 26);

    public static final int DISH_ID1 = START_SEQ + 5;
    public static final int DISH_ID2 = START_SEQ + 6;
    public static final int DISH_ID3 = START_SEQ + 7;
    public static final int DISH_ID4 = START_SEQ + 8;
    public static final int DISH_ID5 = START_SEQ + 9;
    public static final int DISH_ID6 = START_SEQ + 10;

    public static final Dish DISH1 = new Dish(DISH_ID1, "Borsch", new BigDecimal("2.50"), RESTAURANT1, TEST_DATE);
    public static final Dish DISH2 = new Dish(DISH_ID2, "Cutlet", new BigDecimal("1.75"), RESTAURANT1, TEST_DATE);
    public static final Dish DISH3 = new Dish(DISH_ID3, "Stewed fruit", new BigDecimal("0.55"), RESTAURANT1, TEST_DATE);
    public static final Dish DISH4 = new Dish(DISH_ID4, "Saltwort", new BigDecimal("2.60"), RESTAURANT2, TEST_DATE);
    public static final Dish DISH5 = new Dish(DISH_ID5, "Cutlet", new BigDecimal("1.75"), RESTAURANT2, TEST_DATE);
    public static final Dish DISH6 = new Dish(DISH_ID6, "Orange juice", new BigDecimal("0.40"), RESTAURANT2, TEST_DATE);

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
