package ru.dsoccer1980.dishvote.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dsoccer1980.dishvote.model.Restaurant;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.model.UserVote;
import ru.dsoccer1980.dishvote.repository.UserVoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Repository
@Transactional(readOnly = true)
public class UserVoteRepositoryImpl implements UserVoteRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public UserVote save(User user, Restaurant restaurant) {
        //TODO LocalDate.now()
        UserVote userVote = new UserVote(null, user, restaurant, LocalDate.now());
        em.persist(userVote);
        return userVote;
    }

}