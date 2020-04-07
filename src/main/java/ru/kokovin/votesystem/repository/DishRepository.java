package ru.kokovin.votesystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kokovin.votesystem.model.Dish;
import ru.kokovin.votesystem.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.restaurant.id =:restaurantId AND d.dishDate =:currentDate")
    List<Dish> getDishesByRestaurantIdAndMenuDate(@Param("restaurantId")Integer restaurant_id, @Param("currentDate") LocalDate localDate);

    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.id =:id")
    Optional<Dish> getById(@Param("id") int id);

}
