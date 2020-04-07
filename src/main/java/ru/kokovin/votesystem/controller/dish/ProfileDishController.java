package ru.kokovin.votesystem.controller.dish;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.kokovin.votesystem.model.Dish;
import ru.kokovin.votesystem.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = ProfileDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileDishController extends AbstractDishController {
    static final String REST_URL = "/rest/profile/dishes";

    @GetMapping("/current/{restaurantId}")
    public List<Dish> getTodayMenuByRestaurantId(@PathVariable(value = "restaurantId") int restaurantId) {
        LocalDate today = DateTimeUtil.current().toLocalDate();
        return super.getMenuByRestaurantDateAndId(restaurantId, today);
    }

    @GetMapping("/filter/{restaurantId}")
    public List<Dish> getMenuForRestaurantByDate(@PathVariable(value = "restaurantId") int restaurantId,
                                                 @RequestParam(value = "date", required = true)
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return super.getMenuByRestaurantDateAndId(restaurantId, date);
    }
}
