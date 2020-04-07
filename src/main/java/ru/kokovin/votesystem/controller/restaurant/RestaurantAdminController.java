package ru.kokovin.votesystem.controller.restaurant;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kokovin.votesystem.model.Restaurant;

import javax.validation.Valid;


import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = RestaurantAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/admin/restaurants";

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable int id) {
        return super.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = restaurantRepository.save(super.create(restaurant));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> upd(@Valid @RequestBody Restaurant restaurant, @PathVariable int id) {
        return ResponseEntity.ok().body(super.update(restaurant, id));
    }

}
