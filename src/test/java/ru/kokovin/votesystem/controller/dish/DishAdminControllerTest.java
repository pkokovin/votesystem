package ru.kokovin.votesystem.controller.dish;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.kokovin.votesystem.AbstractControllerTest;
import ru.kokovin.votesystem.TestData;
import ru.kokovin.votesystem.TestUtil;
import ru.kokovin.votesystem.model.Dish;
import ru.kokovin.votesystem.model.Restaurant;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kokovin.votesystem.TestData.*;

class DishAdminControllerTest extends AbstractControllerTest {

    public DishAdminControllerTest() {
        super(DishAdminController.REST_URL);
    }

    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHERS.contentJson(DISHES));
    }

    @Test
    void getById() throws Exception {
        perform(doGet(DISH1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHERS.contentJson(DISH1));
    }

    @Test
    void delete() throws Exception {
        perform(doDelete(DISH1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk());
    }

    @Test
    void deleteForbidden() throws Exception {
        perform(doDelete(DISH1_ID).basicAuth(USER))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteUnauthorized() throws Exception {
        perform(doDelete(DISH1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(doDelete(1).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    void createWithLocation() throws Exception {
        Dish newDish = TestData.getNewDish();
        ResultActions action = perform(doPost(String.valueOf(RESTAURANT1_ID)).jsonBody(newDish).basicAuth(ADMIN))
                .andExpect(status().isCreated());

        Dish created = TestUtil.readFromJson(action, Dish.class);
        Integer newId = created.getId();
        newDish.setId(newId);
        DISH_MATCHERS.assertMatch(created, newDish);
        DISH_MATCHERS.assertMatch(dishRepository.getOne(newId), newDish);
    }

    @Test
    void update() throws Exception {
        Dish updated = UPDATED_DISH;
        perform(doPut(DISH1_ID).jsonBody(updated).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk());
        DISH_MATCHERS.assertMatch(dishRepository.getOne(DISH1_ID), UPDATED_DISH);
    }
}