package ru.dsoccer1980.dishvote.service;

import ru.dsoccer1980.dishvote.model.Dish;

import java.util.List;

public interface DishService {
    Dish get(int id);

    List<Dish> getAll();

}