package ru.kokovin.votesystem.controller.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.kokovin.votesystem.AbstractControllerTest;
import ru.kokovin.votesystem.TestData;
import ru.kokovin.votesystem.TestUtil;
import ru.kokovin.votesystem.model.Restaurant;
import ru.kokovin.votesystem.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kokovin.votesystem.TestData.*;
import static ru.kokovin.votesystem.TestData.ADMIN;

class RestaurantAdminControllerTest extends AbstractControllerTest {

    public RestaurantAdminControllerTest() {
        super(RestaurantAdminController.REST_URL);
    }

    @Test
    void delete() throws Exception {
        perform(doDelete(RESTAURANT1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk());
    }

    @Test
    void createWithLocation() throws Exception {
        Restaurant newRestaurant = TestData.getNewRestaurant();
        ResultActions action = perform(doPost().jsonBody(newRestaurant).basicAuth(ADMIN))
                .andExpect(status().isCreated());

        Restaurant created = TestUtil.readFromJson(action, Restaurant.class);
        Integer newId = created.getId();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHERS.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHERS.assertMatch(restaurantRepository.getOne(newId), newRestaurant);
    }

    @Test
    void update() throws Exception {
        Restaurant updated = UPDATED_RESTAURANT;
        perform(doPut(RESTAURANT1_ID).jsonBody(updated).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk());
        RESTAURANT_MATCHERS.assertMatch(restaurantRepository.getOne(RESTAURANT1_ID), UPDATED_RESTAURANT);
    }
}