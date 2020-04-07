package ru.kokovin.votesystem.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.kokovin.votesystem.AbstractControllerTest;
import ru.kokovin.votesystem.TestData;
import ru.kokovin.votesystem.TestUtil;
import ru.kokovin.votesystem.model.User;
import ru.kokovin.votesystem.service.MyUserDetailService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kokovin.votesystem.TestData.*;

class AdminRestControllerTest extends AbstractControllerTest {

    public AdminRestControllerTest() {
        super(AdminRestController.REST_URL);
    }

    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHERS.contentJson(USER, ADMIN));
    }

    @Test
    void enable() throws Exception {
        perform(doPatch(USER_ID).basicAuth(ADMIN).unwrap()
                .param("enabled", "false")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(userService.get(USER_ID).isEnabled());
    }

    @Test
    void getWithId() throws Exception {
        perform(doGet(USER_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHERS.contentJson(USER));
    }

    @Test
    void getNotFound() throws Exception {
        perform(doGet(1).basicAuth(ADMIN))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void getByEmail() throws Exception {
        perform(doGet("by?email={email}", ADMIN.getEmail()).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHERS.contentJson(ADMIN));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(doGet())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getForbidden() throws Exception {
        perform(doGet().basicAuth(USER))
                .andExpect(status().isForbidden());
    }

    @Test
    void create() throws Exception {
        User newUser = TestData.getNewUser();
        ResultActions action = perform(doPost().jsonUserWithPassword(newUser).basicAuth(ADMIN))
                .andExpect(status().isCreated());

        User created = TestUtil.readFromJson(action, User.class);
        Integer newId = created.getId();
        newUser.setId(newId);
        USER_MATCHERS.assertMatch(created, newUser);
        USER_MATCHERS.assertMatch(userService.get(newId), newUser);
    }

    @Test
    void updateUser() throws Exception {
        User updated = UPDATED_USER;
        perform(doPut(USER_ID).jsonBody(updated).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk());
        USER_MATCHERS.assertMatch(userService.get(USER_ID), UPDATED_USER);
    }

    @Test
    void deleteUser() throws Exception {
        perform(doDelete(USER_ID).basicAuth(ADMIN))
                .andExpect(status().isOk());
        USER_MATCHERS.assertMatch(userService.getAll(), ADMIN);
        perform(doGet(USER_ID).basicAuth(ADMIN))
                .andExpect(status().isNotFound());
    }
}