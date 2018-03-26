package ru.dsoccer1980.dishvote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.dishvote.model.Dish;
import ru.dsoccer1980.dishvote.repository.DishRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
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

    @Override
    public List<Dish> getAllDishByRestaurant(int id) {
        return em.createNamedQuery(Dish.GET_ALL_DISH_BY_RESTAURANT, Dish.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    @Transactional
    public Dish save(Dish dish) {
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        }
        else {
            return em.merge(dish);
        }
    }

    @Override
    @Transactional
    public boolean delete(int dishId) {
        return em.createNamedQuery(Dish.DELETE_DISH)
                .setParameter("id", dishId)
                .executeUpdate() != 0;
    }

    @Override
    public List<Dish> getDishOnDate(LocalDate date) {
        return em.createNamedQuery(Dish.GET_DISH_ON_DATE, Dish.class)
                .setParameter("date", date)
                .getResultList();
    }

}