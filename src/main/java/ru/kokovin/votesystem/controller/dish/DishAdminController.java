package ru.kokovin.votesystem.controller.dish;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kokovin.votesystem.model.Dish;
import ru.kokovin.votesystem.model.Restaurant;

import javax.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = DishAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminController extends AbstractDishController {
    static final String REST_URL = "/rest/admin/dishes";

    @GetMapping
    public List<Dish> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok().body(super.getWithId(id));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable int id) {
        return super.delete(id);
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish,
                                                   @PathVariable int restaurantId) {
        Dish created = super.create(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> upd(@Valid @RequestBody Dish dish, @PathVariable int id) {
        return ResponseEntity.ok().body(super.update(dish, id));
    }
}
