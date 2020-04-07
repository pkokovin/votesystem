package ru.kokovin.votesystem.controller.dish;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.kokovin.votesystem.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kokovin.votesystem.TestData.*;


class ProfileDishControllerTest extends AbstractControllerTest {

    public ProfileDishControllerTest() {
        super(ProfileDishController.REST_URL);
    }

    @Test
    void getTodayMenuByRestaurantId() throws Exception {
        perform(doGet("/current/" + RESTAURANT1_ID).basicAuth(USER))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(DISH_MATCHERS.contentJson(DISH1, DISH6, DISH11, DISH16));
    }

    @Test
    void getTodayMenuByNonexistentRestaurantId() throws Exception {
        perform(doGet("/current/" + 1).basicAuth(USER))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTodayMenuByRestaurantIdUnauthorized() throws Exception {
        perform(doGet("/current/" + RESTAURANT1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getMenuForRestaurantByDate() throws Exception {
        perform(doGet("/filter/" + RESTAURANT1_ID).basicAuth(USER).unwrap()
                .param("date", "2020-02-25"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(DISH_MATCHERS.contentJson(DISH21, DISH26, DISH31, DISH36, DISH41));
    }
//    By Admin

        @Test
    void getTodayMenuByRestaurantIdByAdmin() throws Exception {
        perform(doGet("/current/" + RESTAURANT1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHERS.contentJson(TODAYS_RESTAURANT1_MENU));
    }

    @Test
    void getMenuForRestaurantByDateByAdmin() throws Exception {
        perform(doGet("/filter/" + RESTAURANT1_ID).basicAuth(ADMIN).unwrap()
                .param("date", "2020-02-25"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(DISH_MATCHERS.contentJson(DISH21, DISH26, DISH31, DISH36, DISH41));
    }
}