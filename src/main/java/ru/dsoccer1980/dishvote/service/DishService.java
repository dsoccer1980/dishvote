package ru.dsoccer1980.dishvote.service;

import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DishService {
    Dish get(int id) throws NotFoundException;

    Map<Integer, List<Dish>> getAll();

    List<Dish> getAllDishByRestaurant(int id) throws NotFoundException;

    Dish create(Dish dish);

    void delete(int dishId) throws NotFoundException;

    void update(Dish dish) throws NotFoundException;

    Map<Integer, List<Dish>> getDishOnDate(LocalDate date);
}