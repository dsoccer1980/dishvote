package ru.dsoccer1980.dishvote.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;

import java.util.List;

import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.*;

@ContextConfiguration(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT_ID1);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    public void getAll() throws Exception {
        List<Restaurant> restaurantList = service.getAll();
        assertMatch(restaurantList, RESTAURANT1, RESTAURANT2);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void create() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "new name", "new address");
        Restaurant created = service.create(newRestaurant);
        newRestaurant.setId(created.getId());
        assertMatch(service.getAll(), RESTAURANT1, RESTAURANT2, newRestaurant);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT1);
        updated.setName("updated name");
        service.update(updated);
        assertMatch(service.get(RESTAURANT_ID1), updated);
    }

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT_ID1);
        assertMatch(service.getAll(), RESTAURANT2);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

}