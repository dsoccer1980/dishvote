package ru.dsoccer1980.dishvote.service;

import ru.dsoccer1980.dishvote.model.Dish;

import java.util.List;
import java.util.Map;

public interface DishService {
    Dish get(int id);

    Map<Integer, List<Dish>> getAll();

}