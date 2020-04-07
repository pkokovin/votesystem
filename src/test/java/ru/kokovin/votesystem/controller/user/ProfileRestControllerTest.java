package ru.kokovin.votesystem.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.kokovin.votesystem.AbstractControllerTest;
import ru.kokovin.votesystem.model.User;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kokovin.votesystem.TestData.*;
import static ru.kokovin.votesystem.TestUtil.readFromJson;


class ProfileRestControllerTest extends AbstractControllerTest {

    public ProfileRestControllerTest() {
        super(ProfileRestController.REST_URL);
    }

    @Test
    void getSelf() throws Exception {
        perform(doGet().basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHERS.contentJson(USER));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(doGet())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        perform(doDelete().basicAuth(USER))
                .andExpect(status().isOk());
        USER_MATCHERS.assertMatch(userService.getAll(), ADMIN);
    }

    @Test
    void update() throws Exception {
        User updated = UPDATED_USER;
        perform(doPut().jsonBody(updated).basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk());

        USER_MATCHERS.assertMatch(userService.get(USER_ID), UPDATED_USER);
    }

    @Test
    void register() throws Exception {
        User newUser = getNewUser();
        ResultActions action = perform(doPost("/register").jsonBody(newUser))
                .andDo(print())
                .andExpect(status().isCreated());
        User created = readFromJson(action, User.class);
        Integer newId = created.getId();
        newUser.setId(newId);
        USER_MATCHERS.assertMatch(created, newUser);
        USER_MATCHERS.assertMatch(userService.get(newId), newUser);
    }
}