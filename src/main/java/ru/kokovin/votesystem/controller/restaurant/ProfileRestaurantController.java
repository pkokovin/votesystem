package ru.kokovin.votesystem.controller.restaurant;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kokovin.votesystem.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/profile/restaurants";

    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping("/current")
    public List<Restaurant> getAllCurrent() {
        return super.getAllCurrent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getWithId(@PathVariable(value = "id") int restaurant_Id) {
        return ResponseEntity.ok().body(super.getById(restaurant_Id));
    }
}
