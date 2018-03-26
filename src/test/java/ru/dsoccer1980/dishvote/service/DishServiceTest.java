package ru.dsoccer1980.dishvote.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.dsoccer1980.dishvote.testdata.DishTestData.*;
import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT1;
import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT_ID1;
import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.RESTAURANT_ID2;

@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceTest {

    @Autowired
    protected DishService service;

    @Test
    public void create() throws Exception {
        Dish newDish = new Dish(null, "new Dish", new BigDecimal("2.50"), RESTAURANT1, LocalDate.of(2018, 03, 26));
        Dish created = service.create(newDish);
        newDish.setId(created.getId());

        Map<Integer, List<Dish>> expected = new HashMap<>();
        expected.put(RESTAURANT_ID1, Arrays.asList(DISH1, DISH2, DISH3, newDish));
        expected.put(RESTAURANT_ID2, Arrays.asList(DISH4, DISH5, DISH6));
        assertThat(service.getAll()).isEqualTo(expected);
    }

    @Test
    public void get() throws Exception {
        Dish dish = service.get(DISH_ID1);
        assertMatch(dish, DISH1);
    }

    @Test
    public void getAll() throws Exception {
        Map<Integer, List<Dish>> dishMap = service.getAll();
        Map<Integer, List<Dish>> expected = new HashMap<>();
        expected.put(RESTAURANT_ID1, Arrays.asList(DISH1, DISH2, DISH3));
        expected.put(RESTAURANT_ID2, Arrays.asList(DISH4, DISH5, DISH6));
        assertThat(dishMap).isEqualTo(expected);
    }

    @Test
    public void getAllDishByRestaurant() throws Exception {
        assertMatch(service.getAllDishByRestaurant(RESTAURANT_ID1), Arrays.asList(DISH1, DISH2, DISH3));
    }

    @Test
    public void getDishByRestaurant() throws Exception {
        Map<Integer, List<Dish>> expected = new HashMap<>();
        expected.put(RESTAURANT_ID1, Arrays.asList(DISH1, DISH2, DISH3));
        expected.put(RESTAURANT_ID2, Arrays.asList(DISH4, DISH5, DISH6));
        assertThat(service.getDishOnDate(LocalDate.of(2018, 03, 26))).isEqualTo(expected);
    }


    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void update() throws Exception {
        Dish updated = new Dish(DISH1);
        updated.setName("UpdatedDish");
        service.update(updated);
        assertMatch(service.get(DISH_ID1), updated);
    }

    @Test
    public void delete() throws Exception {
        service.delete(DISH_ID1);

        Map<Integer, List<Dish>> dishMap = service.getAll();
        Map<Integer, List<Dish>> expected = new HashMap<>();
        expected.put(RESTAURANT_ID1, Arrays.asList(DISH2, DISH3));
        expected.put(RESTAURANT_ID2, Arrays.asList(DISH4, DISH5, DISH6));
        assertThat(dishMap).isEqualTo(expected);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

}