package ru.dsoccer1980.dishvote.testdata;

import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.model.UserVote;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.dsoccer1980.dishvote.model.AbstractBaseEntity.START_SEQ;
import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT1;
import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT2;
import static ru.dsoccer1980.dishvote.testdata.UserTestData.USER1;
import static ru.dsoccer1980.dishvote.testdata.UserTestData.USER2;

public class UserVoteTestData {

    public static final int USER_VOTE_ID1 = START_SEQ + 11;
    public static final int USER_VOTE_ID2 = START_SEQ + 12;
    public static final int USER_VOTE_ID3 = START_SEQ + 13;

    public static final UserVote USER_VOTE1 = new UserVote(USER_VOTE_ID1, USER1, RESTAURANT1, LocalDate.of(2018, 03, 26));
    public static final UserVote USER_VOTE2 = new UserVote(USER_VOTE_ID2, USER2, RESTAURANT2, LocalDate.of(2018, 03, 26));
    public static final UserVote USER_VOTE3 = new UserVote(USER_VOTE_ID3, USER2, RESTAURANT2, LocalDate.of(2018, 03, 27));

    public static void assertMatch(UserVote actual, UserVote expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<UserVote> actual, UserVote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<UserVote> actual, Iterable<UserVote> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}
