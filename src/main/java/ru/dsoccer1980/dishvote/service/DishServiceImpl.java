package ru.dsoccer1980.dishvote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.repository.DishRepository;
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.dsoccer1980.dishvote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Map<Integer, List<Dish>> getAll() {
        return createMapFromDish(repository.getAll());
    }

    @Override
    public List<Dish> getAllDishByRestaurant(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.getAllDishByRestaurant(id), id);
    }

    @Override
    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish);
    }

    @Override
    public void delete(int dishId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(dishId), dishId);
    }

    @Override
    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        repository.save(dish);
    }

    @Override
    public Map<Integer, List<Dish>> getDishOnDate(LocalDate date) {
        return createMapFromDish(repository.getDishOnDate(date));
    }

    private Map<Integer, List<Dish>> createMapFromDish(List<Dish> dishList) {
        Map<Integer, List<Dish>> result = new HashMap<>();
        for (Dish dish : dishList) {
            Integer restaurantId = dish.getRestaurant().getId();
            if (result.containsKey(restaurantId)) {
                result.get(restaurantId).add(dish);
            } else {
                List<Dish> dishes =new ArrayList<>();
                dishes.add(dish);
                result.put(restaurantId, dishes);
            }
        }
        return result;
    }

}