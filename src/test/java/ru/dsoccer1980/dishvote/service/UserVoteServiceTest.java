package ru.dsoccer1980.dishvote.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.UserVote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.*;
import static ru.dsoccer1980.dishvote.testdata.UserTestData.USER1;
import static ru.dsoccer1980.dishvote.testdata.UserTestData.USER_ID1;
import static ru.dsoccer1980.dishvote.testdata.UserVoteTestData.*;

@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserVoteServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    public void save() throws Exception {
        service.save(USER_ID1, RESTAURANT_ID2, LocalDate.now().plusDays(1));
        assertMatch(service.getAllVotesForUser(USER_ID1), USER_VOTE3,  USER_VOTE1);
    }

    @Test
    public void getAllVotesForUser() throws Exception {
        List<UserVote> allVotesForUser = service.getAllVotesForUser(USER_ID1);
        assertMatch(allVotesForUser, USER_VOTE1);
    }

    @Test
    public void getAllVotesForDate() throws Exception {
        Map<Restaurant, Long> allVotesForDate = service.getAllVotesForDate(LocalDate.of(2018, 03, 26));
        Map<Restaurant, Long> expected = new HashMap<>();
        expected.put(RESTAURANT1, 1L);
        expected.put(RESTAURANT2, 1L);
        assertThat(allVotesForDate).isEqualTo(expected);
    }

    @Test
    public void getUsersVotedByRestaurantAndDate() throws Exception {
        List<User> users = service.getUsersVotedByRestaurantAndDate(RESTAURANT_ID1,LocalDate.of(2018, 03, 26));
        assertThat(users).isEqualTo(Arrays.asList(USER1));
    }

}