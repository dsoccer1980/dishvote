package ru.dsoccer1980.dishvote.service;

import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant get(int id) throws NotFoundException;

    List<Restaurant> getAll();

    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant);

    void delete(int id) throws NotFoundException;
}