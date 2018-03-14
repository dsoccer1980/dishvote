package ru.dsoccer1980.dishvote.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.service.RestaurantService;

import java.util.List;

@Controller
public class RestaurantRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    private final RestaurantService service;

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    public Restaurant get(int id) {
        return service.get(id);
    }

    public List<Restaurant> getAll() {
        return service.getAll();
    }

    public void create(Restaurant restaurant) {
        service.create(restaurant);
    }

    public void update(Restaurant restaurant) {
        service.update(restaurant);
    }

    public void delete(int id) {
        service.delete(id);
    }
}