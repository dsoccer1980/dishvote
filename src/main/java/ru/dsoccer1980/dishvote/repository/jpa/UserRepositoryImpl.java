package ru.dsoccer1980.dishvote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public User get(int id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        }
        else {
            return em.merge(user);
        }
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.GET_ALL, User.class)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE_USER)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public User getByEmail(String email) {
        return em.createNamedQuery(User.GET_BY_EMAIL, User.class)
                .setParameter("email", email)
                .getSingleResult();
    }


}