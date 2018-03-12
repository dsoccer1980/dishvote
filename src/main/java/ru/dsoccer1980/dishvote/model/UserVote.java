package ru.dsoccer1980.dishvote.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "user_vote")
@NamedQueries({
        @NamedQuery(name = UserVote.GET_ALL_USERVOTES, query = "SELECT uv FROM UserVote uv LEFT JOIN FETCH uv.restaurant WHERE uv.user=:user ORDER BY uv.date"),
        @NamedQuery(name = UserVote.GET_USERVOTE, query = "SELECT uv FROM UserVote uv WHERE uv.user=:user AND uv.date=:date"),
        @NamedQuery(name = UserVote.UPDATE_USERVOTE, query = "UPDATE UserVote uv SET uv.restaurant=:restaurant WHERE uv.user=:user AND uv.date=:date")
})
public class UserVote extends AbstractBaseEntity {

    public static final String GET_ALL_USERVOTES = "UserVote.getAllUserVotes";
    public static final String GET_USERVOTE = "UserVote.getUserVote";
    public static final String UPDATE_USERVOTE = "UserVote.updateUserVote";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
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


    public UserVote(Integer id, User user, Restaurant restaurant, LocalDate date) {
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