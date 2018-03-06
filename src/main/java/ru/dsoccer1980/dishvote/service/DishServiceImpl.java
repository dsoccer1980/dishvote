package ru.dsoccer1980.dishvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.repository.DishRepository;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }

}