package ru.kokovin.votesystem.controller.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kokovin.votesystem.controller.AbstractController;
import ru.kokovin.votesystem.exception.ResourceNotFoundException;
import ru.kokovin.votesystem.model.Dish;
import ru.kokovin.votesystem.repository.DishRepository;
import ru.kokovin.votesystem.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.kokovin.votesystem.util.ValidationUtil.*;

public abstract class AbstractDishController extends AbstractController {
    @Autowired
    protected DishRepository dishRepository;
    @Autowired
    protected RestaurantRepository restaurantRepository;

    public List<Dish> getAll() {
        log.info("get all dishes");
        return dishRepository.findAll();
    }

    public Dish getWithId(@PathVariable(value = "id") Integer id) {
        log.info("get dish with id = " + id);
        return dishRepository.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no dish with id = " + id));
    }

    public List<Dish> getMenuByRestaurantDateAndId(int restaurantId, LocalDate date) {
        checkNotFoundWithId(restaurantRepository.existsById(restaurantId), restaurantId);
        log.info("get menu of restaurant with id = {} and date = {}", restaurantId, date);
        return dishRepository.getDishesByRestaurantIdAndMenuDate(restaurantId, date);
    }

    public Map<String, Boolean> delete(int id) {
        log.info("try to delete dish with id : " + id);
        Dish dish = dishRepository.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found : " + id));
        dishRepository.delete(dish);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        log.info("create dish {} for restaurant id {}", dish, restaurantId);
        dish.setRestaurant(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found : " + restaurantId)));
        return dishRepository.save(dish);
    }

    public Dish update(Dish dish, int id) {
        Assert.notNull(dish, "restaurant must not be null");
        assureIdConsistent(dish, id);
        log.info("update dish id {} with dish {} ", id, dish);
        Dish updated = dishRepository.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found : " + id));
        updated.setPrice(dish.getPrice() == null? updated.getPrice(): dish.getPrice());
        updated.setName(dish.getName() == null? updated.getName(): dish.getName());
        updated.setdishDate(dish.getdishDate() == null? updated.getdishDate(): dish.getdishDate());
        return dishRepository.save(updated);
    }
}
