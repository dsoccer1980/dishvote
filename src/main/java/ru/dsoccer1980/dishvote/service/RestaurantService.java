package ru.dsoccer1980.dishvote.service;

import ru.dsoccer1980.dishvote.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant get(int id);

    List<Restaurant> getAll();

    void save(String restaurantName, String restaurantAddress);
}