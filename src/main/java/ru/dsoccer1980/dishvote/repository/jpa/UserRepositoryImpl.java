package ru.dsoccer1980.dishvote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public void save(User user) {
        if (user.isNew()) {
            em.persist(user);
        }
        else {
            em.merge(user);
        }
    }


}