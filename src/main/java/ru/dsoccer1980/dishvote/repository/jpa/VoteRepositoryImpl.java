package ru.dsoccer1980.dishvote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.UserVote;
import ru.dsoccer1980.dishvote.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Transactional(readOnly = true)
public class VoteRepositoryImpl implements VoteRepository {

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
            userVote = em.createNamedQuery(UserVote.GET_USERVOTE_BY_User_AND_DATE, UserVote.class)
                    .setParameter("user", user)
                    .setParameter("date", date)
                    .getSingleResult();
        } catch (NoResultException e) {
            userVote = new UserVote(null, user, restaurant, date);
        }

        return userVote;
    }

    @Override
    public Map<Integer, Long> getVotesForRestaurantOnDate(LocalDate date) {
        List<Object[]> votesForDate = em.createNamedQuery(UserVote.GET_USERVOTES_FOR_RESTAURANT_ON_DATE, Object[].class)
                .setParameter("date", date)
                .getResultList();

        Map<Integer, Long> result = new ConcurrentHashMap<>();
        for (Object[] vote : votesForDate) {
            result.put((Integer)vote[1], Long.parseLong(vote[0].toString()));
        }

        return result;
    }

    @Override
    public List<User> getUsersVotedByRestaurantAndDate(int restaurantId, LocalDate date) {
        return em.createNamedQuery(UserVote.GET_USERVOTES_BY_RESTAURANT_AND_DATE, User.class)
                .setParameter("restaurant_id", restaurantId)
                .setParameter("date", date)
                .getResultList();
    }

}