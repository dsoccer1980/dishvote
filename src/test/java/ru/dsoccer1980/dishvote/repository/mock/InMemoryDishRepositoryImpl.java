package ru.dsoccer1980.dishvote.repository.mock;

import org.springframework.stereotype.Repository;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class InMemoryDishRepositoryImpl implements DishRepository {

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    public void init() {
        repository.clear();

    }

    @Override
    public Dish get(int id) {
        return null;
    }

    @Override
    public List<Dish> getAll() {
        return null;
    }

    @Override
    public List<Dish> getAllDishByRestaurant(int id) {
        return null;
    }

    @Override
    public Dish save(Dish dish) {
        return null;
    }

    @Override
    public boolean delete(int dishId) {
        return false;
    }

    @Override
    public List<Dish> getDishOnDate(LocalDate date) {
        return null;
    }
}
