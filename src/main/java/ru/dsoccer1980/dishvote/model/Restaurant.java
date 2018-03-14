package ru.dsoccer1980.dishvote.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "restaurant")
@NamedQueries({
        @NamedQuery(name = Restaurant.GET_ALL_RESTAURANTS, query = "SELECT r FROM Restaurant r"),
        @NamedQuery(name = Restaurant.DELETE_RESTAURANT, query = "DELETE FROM Restaurant r WHERE r.id=:id")
})
public class Restaurant extends AbstractNamedEntity {

    public static final String GET_ALL_RESTAURANTS = "Restaurant.getAllRestaurants";
    public static final String DELETE_RESTAURANT = "Restaurant.deleteRestaurant";

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(max = 100)
    private String address;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getAddress());
    }


    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    public Restaurant(String name, String address) {
        super(null, name);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + id +
                ", name=" + name +
                ", address=" + address +
                '}';
    }
}