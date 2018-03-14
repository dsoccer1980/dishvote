package ru.dsoccer1980.dishvote.service;

import ru.dsoccer1980.dishvote.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant get(int id);

    List<Restaurant> getAll();

    void create(Restaurant restaurant);

    void update(Restaurant restaurant);
}