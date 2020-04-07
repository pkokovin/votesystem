package ru.kokovin.votesystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {
//    price in kop
//    see http://stackoverflow.com/a/43051227/548473
    @Column(name = "price")
    private Long price;

    @Column(name = "menu_date")
    @NotNull
    private LocalDate dishDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    protected Dish(){}

    public Dish(String name, Long price, LocalDate dishDate) {
        super(null, name);
        this.price = price;
        this.dishDate = dishDate;
    }

    public Dish(Integer id, String name, Long price, LocalDate dishDate) {
        super(id, name);
        this.price = price;
        this.dishDate = dishDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDate getdishDate() {
        return dishDate;
    }

    public void setdishDate(LocalDate dishDate) {
        this.dishDate = dishDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "price=" + price +
                ", dishDate=" + dishDate +
                ", restaurant=" + restaurant +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
