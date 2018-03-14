package ru.dsoccer1980.dishvote.repository;


import ru.dsoccer1980.dishvote.model.Dish;

import java.util.List;

public interface DishRepository {

    Dish get(int id);

    List<Dish> getAll();

    List<Dish> getAllDishByRestaurant(int id);
}
