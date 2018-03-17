package ru.dsoccer1980.dishvote.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.service.DishService;

import java.util.List;
import java.util.Map;

@Controller
public class DishRestController {
    private static final Logger log = LoggerFactory.getLogger(DishRestController.class);

    private final DishService service;

    @Autowired
    public DishRestController(DishService service) {
        this.service = service;
    }

    public Dish get(int id) {
        return service.get(id);
    }

    public Map<Integer, List<Dish>> getAll() {
        return service.getAll();
    }

    public List<Dish> getAllDishByRestaurant(int id) {
        return service.getAllDishByRestaurant(id);
    }

    public void create(Dish dish) {
        service.create(dish);
    }

    public boolean delete(int dishId) {
        return service.delete(dishId);
    }

    public void update(Dish dish) {
        service.update(dish);
    }
}