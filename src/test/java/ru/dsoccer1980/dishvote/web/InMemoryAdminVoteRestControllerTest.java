package ru.dsoccer1980.dishvote.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.repository.mock.InMemoryRestaurantRepositoryImpl;
import ru.dsoccer1980.dishvote.repository.mock.InMemoryUserRepositoryImpl;
import ru.dsoccer1980.dishvote.repository.mock.InMemoryUserVoteRepositoryImpl;
import ru.dsoccer1980.dishvote.web.vote.AdminVoteRestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.dsoccer1980.dishvote.testdata.DishTestData.TEST_DATE;
import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT1;

import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT_ID1;
import static ru.dsoccer1980.dishvote.testdata.UserTestData.USER1;

@ContextConfiguration({"classpath:spring/test-spring-app.xml", "classpath:spring/test-spring-db.xml"})
@RunWith(SpringRunner.class)
public class InMemoryAdminVoteRestControllerTest {

//    @Autowired
//    private AdminVoteRestController controller;
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
//    public void testGetAllVotesForDate() {
//        Map<Restaurant, Long> allVotesForDate = controller.getVotesForRestaurantOnDate(TEST_DATE);
//        Map<Restaurant, Long> expected = new ConcurrentHashMap<>();
//        expected.put(RESTAURANT1, 1L);
//        assertThat(allVotesForDate).isEqualTo(expected);
//    }
//
//    @Test
//    public void testGetVotesOfUsersByRestaurantAndDate() {
//        List<User> votesOfUsersByRestaurantAndDate = controller.getVotesOfUsersByRestaurantAndDate(RESTAURANT_ID1, TEST_DATE);
//        assertThat(votesOfUsersByRestaurantAndDate).isEqualTo(Arrays.asList(USER1));
//    }

}
