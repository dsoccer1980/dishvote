package ru.dsoccer1980.dishvote.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.repository.mock.InMemoryDishRepositoryImpl;
import ru.dsoccer1980.dishvote.repository.mock.InMemoryRestaurantRepositoryImpl;
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;
import ru.dsoccer1980.dishvote.web.dish.DishRestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.dsoccer1980.dishvote.testdata.DishTestData.*;
import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT1;
import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT_ID1;

@ContextConfiguration({"classpath:spring/test-spring-app.xml", "classpath:spring/test-spring-db.xml"})
@RunWith(SpringRunner.class)
public class InMemoryDishRestControllerTest {

    @Autowired
    private DishRestController controller;

    @Autowired
    private InMemoryDishRepositoryImpl repository;

    @Autowired
    private InMemoryRestaurantRepositoryImpl restaurantRepository;

    @Before
    public void setUp() throws Exception {
        repository.init();
        restaurantRepository.init();
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(DISH_ID1);
        List<Dish> dishAll = controller.getAll().values().iterator().next();
        Assert.assertEquals(dishAll.size(), 1);
        Assert.assertEquals(dishAll.iterator().next(), DISH2);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        controller.delete(10);
    }

    @Test
    public void testGet() {
        Dish dish = controller.get(DISH_ID1);
        assertMatch(dish, DISH1);
    }

    @Test
    public void testGetAllDishByRestaurant() {
        List<Dish> allDishByRestaurant = controller.getAllDishByRestaurant(RESTAURANT_ID1);
        assertMatch(allDishByRestaurant, DISH1, DISH2);
    }

    @Test
    public void testCreate() {
        Dish newDish = new Dish(null, "newName", new BigDecimal("2.3"), RESTAURANT1, LocalDate.now());
        controller.create(newDish);
        assertMatch(controller.getAllDishByRestaurant(RESTAURANT_ID1), DISH1, newDish, DISH2);
    }

    @Test
    public void testUpdate() {
        Dish updateDish = new Dish(DISH1);
        updateDish.setName("new Name");
        controller.update(updateDish);
        assertMatch(controller.getAllDishByRestaurant(RESTAURANT_ID1), DISH1, DISH2);
    }

    @Test
    public void testGetDishOnDate() {
        Map<Integer, List<Dish>> dishOnDate = controller.getDishOnDate(TEST_DATE);
        assertThat(dishOnDate.values().toArray()).isEqualTo(new Object[]{Arrays.asList(DISH1, DISH2)});
    }


}
