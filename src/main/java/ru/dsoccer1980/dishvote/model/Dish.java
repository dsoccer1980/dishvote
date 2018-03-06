package ru.dsoccer1980.dishvote.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "dish")
@NamedQuery(name = Dish.ALL_DISHES, query = "SELECT d FROM Dish d LEFT JOIN FETCH d.restaurant")
public class Dish extends AbstractNamedEntity {

    public static final String ALL_DISHES = "Dish.getAll";

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    public Dish() {
    }

    public Dish(Dish r) {
        this(r.getId(), r.getName(), r.getPrice(), r.getRestaurant(), r.getDate());
    }


    public Dish(Integer id, String name, BigDecimal price, Restaurant restaurant, LocalDate date) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        return "Dish{" +
                "dishId=" + id +
                ", restaurantId=" + restaurant.getId() +
                ", name='" + name +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}