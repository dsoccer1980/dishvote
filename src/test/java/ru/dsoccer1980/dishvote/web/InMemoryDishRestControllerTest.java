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
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;
import ru.dsoccer1980.dishvote.web.dish.DishRestController;

import java.util.List;

import static ru.dsoccer1980.dishvote.testdata.DishTestData.*;

@ContextConfiguration({"classpath:spring/test-spring-app.xml", "classpath:spring/test-spring-db.xml"})
@RunWith(SpringRunner.class)
public class InMemoryDishRestControllerTest {

    @Autowired
    private DishRestController controller;

    @Autowired
    private InMemoryDishRepositoryImpl repository;

    @Before
    public void setUp() throws Exception {
        repository.init();
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

}
