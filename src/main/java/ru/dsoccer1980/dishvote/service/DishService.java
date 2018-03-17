package ru.dsoccer1980.dishvote.service;

import ru.dsoccer1980.dishvote.model.Dish;

import java.util.List;
import java.util.Map;

public interface DishService {
    Dish get(int id);

    Map<Integer, List<Dish>> getAll();

    List<Dish> getAllDishByRestaurant(int id);

    void create(Dish dish);

    boolean delete(int dishId);

    void update(Dish dish);
}