package ru.dsoccer1980.dishvote.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.service.DishService;

import java.time.LocalDate;
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
        log.info("get Dish(id):  {}", id);
        return service.get(id);
    }

    public Map<Integer, List<Dish>> getAll() {
        log.info("get all dish");
        return service.getAll();
    }

    public List<Dish> getAllDishByRestaurant(int id) {
        log.info("get Dish by restaurant(id):  {}", id);
        return service.getAllDishByRestaurant(id);
    }

    public void create(Dish dish) {
        log.info("create dish:  {}", dish);
        service.create(dish);
    }

    public boolean delete(int dishId) {
        log.info("delete Dish(id):  {}", dishId);
        return service.delete(dishId);
    }

    public void update(Dish dish) {
        log.info("update Dish:  {}", dish);
        service.update(dish);
    }

    public Map<Integer, List<Dish>> getDishOnDate(LocalDate date) {
        log.info("get Dish on date:  {}", date);
        return service.getDishOnDate(date);
    }
}