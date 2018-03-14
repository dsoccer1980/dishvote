package ru.dsoccer1980.dishvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public void create(Restaurant restaurant) {
        repository.save(restaurant);

    }

    @Override
    public void update(Restaurant restaurant) {
        repository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

}