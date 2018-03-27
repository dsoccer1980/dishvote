package ru.dsoccer1980.dishvote.repository.mock;

import org.springframework.stereotype.Repository;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.repository.DishRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.dsoccer1980.dishvote.testdata.DishTestData.*;


@Repository
public class InMemoryDishRepositoryImpl implements DishRepository {

    private Map<Integer, Dish> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    public void init() {
        repository.clear();
        repository.put(DISH_ID1, DISH1);
        repository.put(DISH_ID2, DISH2);
    }

    @Override
    public Dish get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Dish> getAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public List<Dish> getAllDishByRestaurant(int id) {
        return getAll().stream().filter((d) -> d.getRestaurant().getId() == id).collect(Collectors.toList());
    }

    @Override
    public Dish save(Dish dish) {
        if (dish.isNew()) {
            dish.setId(counter.incrementAndGet());
            repository.put(dish.getId(), dish);
            return dish;
        }
        return repository.computeIfPresent(dish.getId(), (id, oldDish) -> dish);
    }

    @Override
    public boolean delete(int dishId) {
        return repository.remove(dishId) != null;
    }

    @Override
    public List<Dish> getDishOnDate(LocalDate date) {
        return getAll().stream().filter((d) -> d.getDate().equals(date)).collect(Collectors.toList());
    }
}
