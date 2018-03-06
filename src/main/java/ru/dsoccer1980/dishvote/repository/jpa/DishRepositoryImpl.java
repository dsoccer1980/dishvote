package ru.dsoccer1980.dishvote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.repository.DishRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DishRepositoryImpl implements DishRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Dish get(int id) {
        Dish dish = em.find(Dish.class, id);
        return dish;
    }

    @Override
    public List<Dish> getAll() {
        return em.createNamedQuery(Dish.ALL_DISHES, Dish.class).getResultList();
    }

}