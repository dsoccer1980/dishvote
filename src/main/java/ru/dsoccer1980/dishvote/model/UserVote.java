package ru.dsoccer1980.dishvote.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "user_vote")
@NamedQueries({
        @NamedQuery(name = UserVote.GET_ALL_USERVOTES, query = "SELECT uv FROM UserVote uv LEFT JOIN FETCH uv.restaurant WHERE uv.user=:user ORDER BY uv.date DESC"),
        @NamedQuery(name = UserVote.GET_USERVOTES_BY_RESTAURANT_AND_DATE, query = "SELECT u FROM UserVote uv LEFT JOIN User u ON uv.user.id=u.id WHERE uv.restaurant.id=:restaurant_id AND uv.date=:date"),
        @NamedQuery(name = UserVote.GET_USERVOTE_BY_User_AND_DATE, query = "SELECT uv FROM UserVote uv WHERE uv.user=:user AND uv.date=:date"),
        @NamedQuery(name = UserVote.UPDATE_USERVOTE, query = "UPDATE UserVote uv SET uv.restaurant=:restaurant WHERE uv.user=:user AND uv.date=:date"),
        @NamedQuery(name = UserVote.GET_VOTES_FOR_DATE, query = "SELECT count(uv.restaurant.id), r.id, r.name FROM Restaurant r LEFT JOIN UserVote uv ON r.id=uv.restaurant.id AND uv.date=:date GROUP BY uv.restaurant.id, r.id ORDER BY count(uv.restaurant.id) DESC, r.name"),
})
public class UserVote extends AbstractBaseEntity {

    public static final String GET_ALL_USERVOTES = "UserVote.getAllUserVotes";
    public static final String GET_USERVOTES_BY_RESTAURANT_AND_DATE = "UserVote.getUserVotesByRestaurantAndDate";
    public static final String GET_USERVOTE_BY_User_AND_DATE = "UserVote.getUserVoteByUserAndDate";
    public static final String UPDATE_USERVOTE = "UserVote.updateUserVote";
    public static final String GET_VOTES_FOR_DATE = "UserVote.getVotesForDate";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    public UserVote() {
    }

    public UserVote(UserVote v) {
        this(v.getId(), v.getUser(), v.getRestaurant(), v.getDate());
    }


    public UserVote(Integer id, @NotNull User user, @NotNull Restaurant restaurant, @NotNull LocalDate date) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserVote{" +
                "dishId=" + id +
                ", userId=" + user.getId() +
                ", restaurantId=" + restaurant.getId() +
                ", date=" + date +
                '}';
    }
}