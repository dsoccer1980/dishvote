package ru.dsoccer1980.dishvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.repository.DishRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish get(int id) {
        return repository.get(id);
    }

    @Override
    public Map<Integer, List<Dish>> getAll() {
        Map<Integer, List<Dish>> result = new HashMap<>();
        for (Dish dish : repository.getAll()) {
            Integer restaurantId = dish.getRestaurant().getId();
            if (result.containsKey(restaurantId)) {
                result.get(restaurantId).add(dish);
            } else {
                List<Dish> dishes =new ArrayList<>();
                dishes.add(dish);
                result.put(restaurantId, dishes);
            }
        }
        return result;
    }

    @Override
    public List<Dish> getAllDishByRestaurant(int id) {
        return repository.getAllDishByRestaurant(id);
    }

}