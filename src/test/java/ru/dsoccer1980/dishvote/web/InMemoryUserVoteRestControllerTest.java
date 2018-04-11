package ru.dsoccer1980.dishvote.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dsoccer1980.dishvote.model.UserVote;
import ru.dsoccer1980.dishvote.repository.mock.InMemoryRestaurantRepositoryImpl;
import ru.dsoccer1980.dishvote.repository.mock.InMemoryUserRepositoryImpl;
import ru.dsoccer1980.dishvote.repository.mock.InMemoryUserVoteRepositoryImpl;
import ru.dsoccer1980.dishvote.util.Exception.VoteException;
import ru.dsoccer1980.dishvote.web.vote.UserVoteRestController;

import java.time.LocalDate;
import java.util.List;

import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT_ID1;
import static ru.dsoccer1980.dishvote.testdata.UserTestData.*;
import static ru.dsoccer1980.dishvote.testdata.UserVoteTestData.*;

@ContextConfiguration({"classpath:spring/test-spring-app.xml", "classpath:spring/test-spring-db.xml"})
@RunWith(SpringRunner.class)
public class InMemoryUserVoteRestControllerTest {

//    @Autowired
//    private UserVoteRestController controller;
//
//    @Autowired
//    private InMemoryUserVoteRepositoryImpl repository;
//
//    @Autowired
//    private InMemoryUserRepositoryImpl userRepository;
//
//    @Autowired
//    private InMemoryRestaurantRepositoryImpl restaurantRepository;
//
//    @Before
//    public void setUp() throws Exception {
//        repository.init();
//        userRepository.init();
//        restaurantRepository.init();
//    }
//
//    @Test
//    public void testGetAllVotesForUser() {
//        List<UserVote> allVotesForUser = controller.getAllVotesForUser(USER_ID1);
//        assertMatch(allVotesForUser, USER_VOTE1);
//    }
//
//    @Test
//    public void testSave() {
//        UserVote newUserVote = controller.save(USER_ID1, RESTAURANT_ID1, LocalDate.now().plusDays(1));
//        List<UserVote> allVotesForUser = controller.getAllVotesForUser(USER_ID1);
//        assertMatch(allVotesForUser, newUserVote, USER_VOTE1);
//    }
//
//    @Test(expected = VoteException.class)
//    public void testSaveCannotVoteAfterDeadLine() {
//        UserVote newUserVote = controller.save(USER_ID1, RESTAURANT_ID1, LocalDate.now().minusDays(1));
//        List<UserVote> allVotesForUser = controller.getAllVotesForUser(USER_ID1);
//        assertMatch(allVotesForUser, newUserVote, USER_VOTE1);
//    }

}
