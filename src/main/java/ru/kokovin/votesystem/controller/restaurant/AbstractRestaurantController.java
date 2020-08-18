package ru.kokovin.votesystem.controller.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kokovin.votesystem.controller.AbstractController;
import ru.kokovin.votesystem.exception.ResourceNotFoundException;
import ru.kokovin.votesystem.model.Restaurant;
import ru.kokovin.votesystem.repository.DishRepository;
import ru.kokovin.votesystem.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.kokovin.votesystem.util.DateTimeUtil.*;
import static ru.kokovin.votesystem.util.ValidationUtil.*;

public abstract class AbstractRestaurantController extends AbstractController {
    @Autowired
    protected RestaurantRepository restaurantRepository;
    @Autowired
    protected DishRepository dishRepository;

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantRepository.findAll();
    }

    public List<Restaurant> getAllCurrent() {
        log.info("get all restaurants");
        LocalDate current = current().toLocalDate();
        List<Restaurant> restaurants = restaurantRepository.findAll();
        for (Restaurant restaurant: restaurants) {
            restaurant.setMenu(dishRepository.getDishesByRestaurantIdAndMenuDate(restaurant.getId(), current));
        }
        return restaurants;
    }

    public Restaurant getById(@PathVariable(value = "id") int restaurant_Id) {
        log.info("get restaurant with id = " + restaurant_Id);
        return restaurantRepository.findById(restaurant_Id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no restaurant with id = " + restaurant_Id));
    }

    public Restaurant update(Restaurant restaurant, int id) {
        Assert.notNull(restaurant, "restaurant must not be null");
        assureIdConsistent(restaurant, id);
        log.info("update restaurant id {} with restaurant {} ", id, restaurant);
        checkNotFoundWithId(restaurantRepository.existsById(id), id);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {} ", restaurant);
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    public Map<String, Boolean> delete(int restaurantId) {
        log.info("try to delete restaurant with id : " + restaurantId);
        restaurantRepository.delete(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found : " + restaurantId)));
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
