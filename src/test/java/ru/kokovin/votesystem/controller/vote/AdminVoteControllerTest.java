package ru.kokovin.votesystem.controller.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.kokovin.votesystem.AbstractControllerTest;
import ru.kokovin.votesystem.TestUtil;
import ru.kokovin.votesystem.model.Vote;
import ru.kokovin.votesystem.util.DateTimeUtil;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kokovin.votesystem.TestData.*;
import static ru.kokovin.votesystem.TestData.ADMIN;

class AdminVoteControllerTest extends AbstractControllerTest {

    public AdminVoteControllerTest() {
        super(AdminVoteController.REST_URL);
    }



    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(VOTE_MATCHERS.contentJson(ALLVOTES));
    }

    @Test
    void getUserVotesByUserId() throws Exception {
        perform(doGet("/user/" + USER_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(VOTE_MATCHERS.contentJson(USER_VOTES));
    }

    @Test
    void getById() throws Exception {
        perform(doGet(VOTE1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE1));
    }

    @Test
    void delete() throws Exception {
        perform(doDelete(VOTE1_ID).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk());
        perform(doGet().basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE2, VOTE3, VOTE4));
    }
}