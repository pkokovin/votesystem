package ru.kokovin.votesystem.controller.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.kokovin.votesystem.AbstractControllerTest;
import ru.kokovin.votesystem.model.Restaurant;

import java.util.List;

import static ru.kokovin.votesystem.TestUtil.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kokovin.votesystem.TestData.*;

class ProfileRestaurantControllerTest extends AbstractControllerTest {

    public ProfileRestaurantControllerTest() {
        super(ProfileRestaurantController.REST_URL);
    }

    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(USER))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHERS.contentJson(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5));
    }

    @Test
    void getById() throws Exception {
        perform(doGet(RESTAURANT1_ID).basicAuth(USER))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHERS.contentJson(RESTAURANT1));
    }

    @Test
    void getAllCurrent() throws Exception {
        perform(doGet("/current").basicAuth(USER))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void getNotFoundById() throws Exception {
        perform(doGet(1).basicAuth(ADMIN))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(doGet())
                .andExpect(status().isUnauthorized());
    }

//    ByAdmin

    @Test
    void getAllByAdmin() throws Exception {
        perform(doGet().basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHERS.contentJson(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5));
    }

    @Test
    void getByIdByAdmin() throws Exception {
        perform(doGet(RESTAURANT1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHERS.contentJson(RESTAURANT1));
    }

    @Test
    void getNotFoundByIdByAdmin() throws Exception {
        perform(doGet(1).basicAuth(ADMIN))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUnauthorizedByAdmin() throws Exception {
        perform(doGet())
                .andExpect(status().isUnauthorized());
    }

}