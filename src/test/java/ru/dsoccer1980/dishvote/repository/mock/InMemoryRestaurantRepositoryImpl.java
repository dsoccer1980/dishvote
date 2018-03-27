package ru.dsoccer1980.dishvote.repository.mock;

import org.springframework.stereotype.Repository;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.dsoccer1980.dishvote.testdata.RestaurantTestData.*;

@Repository
public class InMemoryRestaurantRepositoryImpl implements RestaurantRepository {

    private Map<Integer, Restaurant> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    public void init() {
        repository.clear();
        repository.put(RESTAURANT_ID1, RESTAURANT1);
        repository.put(RESTAURANT_ID2, RESTAURANT2);
    }

    @Override
    public Restaurant get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return new ArrayList<Restaurant>(repository.values());
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            restaurant.setId(counter.incrementAndGet());
            repository.put(restaurant.getId(), restaurant);
            return restaurant;
        }
        return repository.computeIfPresent(restaurant.getId(), (id, oldRestaurant) -> restaurant);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }
}
