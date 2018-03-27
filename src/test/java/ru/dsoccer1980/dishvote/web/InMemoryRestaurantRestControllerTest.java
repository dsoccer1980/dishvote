package ru.dsoccer1980.dishvote.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.repository.mock.InMemoryRestaurantRepositoryImpl;
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;
import ru.dsoccer1980.dishvote.web.restaurant.RestaurantRestController;

import java.util.List;

import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.*;

@ContextConfiguration({"classpath:spring/test-spring-app.xml", "classpath:spring/test-spring-db.xml"})
@RunWith(SpringRunner.class)
public class InMemoryRestaurantRestControllerTest {

    @Autowired
    private RestaurantRestController controller;

    @Autowired
    private InMemoryRestaurantRepositoryImpl repository;

    @Before
    public void setUp() throws Exception {
        repository.init();
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(RESTAURANT_ID1);
        List<Restaurant> restaurant = controller.getAll();
        Assert.assertEquals(restaurant.size(), 1);
        Assert.assertEquals(restaurant.iterator().next(), RESTAURANT2);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(10);
    }

    @Test
    public void testGet() {
        Restaurant restaurant = controller.get(RESTAURANT_ID1);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    public void testCreate() {
        Restaurant newRestaurant = new Restaurant(null, "newRestaurant", "newAddress");
        controller.create(newRestaurant);
        assertMatch(controller.getAll(), RESTAURANT1, RESTAURANT2, newRestaurant);
    }

    @Test
    public void testUpdate() {
        Restaurant updateRestaurant = new Restaurant(RESTAURANT1);
        updateRestaurant.setName("new Name");
        controller.update(updateRestaurant);
        assertMatch(controller.getAll(), RESTAURANT1, RESTAURANT2);
    }

}
