package ru.dsoccer1980.dishvote.repository;


import ru.dsoccer1980.dishvote.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    Dish get(int id);

    List<Dish> getAll();

    List<Dish> getAllDishByRestaurant(int id);

    void save(Dish dish);

    boolean delete(int dishId);

    List<Dish> getDishOnDate(LocalDate date);
}
