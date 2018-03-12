package ru.dsoccer1980.dishvote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.UserVote;
import ru.dsoccer1980.dishvote.repository.UserVoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserVoteRepositoryImpl implements UserVoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserVote save(User user, Restaurant restaurant, LocalDate date) {

        UserVote userVote = getUserVote(user, restaurant, date);
        if (userVote.isNew()) {
            em.persist(userVote);
        }
        else {
            em.createNamedQuery(UserVote.UPDATE_USERVOTE)
                    .setParameter("user", user)
                    .setParameter("date", date)
                    .setParameter("restaurant", restaurant)
                    .executeUpdate();
        }

        return userVote;
    }

    @Override
    public List<UserVote> getAllVotesForUser(User user) {
        return em.createNamedQuery(UserVote.GET_ALL_USERVOTES, UserVote.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public UserVote getUserVote(User user, Restaurant restaurant, LocalDate date) {
        UserVote userVote;
        try {
            userVote = em.createNamedQuery(UserVote.GET_USERVOTE, UserVote.class)
                    .setParameter("user", user)
                    .setParameter("date", date)
                    .getSingleResult();
        } catch (NoResultException e) {
            userVote = new UserVote(null, user, restaurant, date);
        }

        return userVote;
    }

    @Override
    public boolean isNew(User user) {
        return user.getId() == null;
    }

}