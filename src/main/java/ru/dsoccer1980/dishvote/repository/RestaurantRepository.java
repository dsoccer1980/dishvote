package ru.dsoccer1980.dishvote.repository;


import ru.dsoccer1980.dishvote.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant save(Restaurant restaurant);


}
