package ru.kokovin.votesystem.controller.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.kokovin.votesystem.AbstractControllerTest;
import ru.kokovin.votesystem.TestUtil;
import ru.kokovin.votesystem.model.Vote;
import ru.kokovin.votesystem.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.kokovin.votesystem.TestData.*;

class ProfileVoteControllerTest extends AbstractControllerTest {

    public ProfileVoteControllerTest() {
        super(ProfileVoteController.REST_URL);
    }

    @Test
    void getAllOwn() throws Exception {
        perform(doGet().basicAuth(USER))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(VOTE_MATCHERS.contentJson(USER_VOTES));
    }

    @Test
    void getAllOwnAdmin() throws Exception {
        perform(doGet().basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(VOTE_MATCHERS.contentJson(ADMIN_VOTES));
    }

    @Test
    void createNewWithLocationBefore11() throws Exception {
        LocalDateTime test10_00 = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0));
        Vote voteNew = new Vote(USER, RESTAURANT1, test10_00);
        DateTimeUtil.useFixedClockAt(test10_00);
        ResultActions action = perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(USER))
                .andExpect(status().isCreated())
                .andDo(print());
        Vote created = TestUtil.readFromJson(action, Vote.class);
        voteNew.setId(created.getId());

        perform(doGet().basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE1, VOTE2, voteNew));
    }

    @Test
    void updateWithLocationBefore11() throws Exception {
        LocalDateTime test10_00 = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0));
        LocalDateTime test10_30 = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30));
        Vote voteNew = new Vote(USER, RESTAURANT1, test10_00);
        Vote voteUpdated = new Vote(USER, RESTAURANT1, test10_30);
        DateTimeUtil.useFixedClockAt(test10_00);
        ResultActions action = perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(USER))
                .andExpect(status().isCreated())
                .andDo(print());
        Vote created = TestUtil.readFromJson(action, Vote.class);
        voteNew.setId(created.getId());
        perform(doGet().basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE1, VOTE2, voteNew));
        DateTimeUtil.useFixedClockAt(test10_30);
        ResultActions action2 = perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(USER))
                .andExpect(status().isCreated())
                .andDo(print());
        Vote updated = TestUtil.readFromJson(action2, Vote.class);
        voteUpdated.setId(updated.getId());
        perform(doGet().basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE1, VOTE2, voteUpdated));
    }

    @Test
    void createNewWithLocationAfter11() throws Exception {
        LocalDateTime test12_00 = LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0));
        Vote voteNew = new Vote(USER, RESTAURANT1, test12_00);
        DateTimeUtil.useFixedClockAt(test12_00);
        ResultActions action = perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(USER))
                .andExpect(status().isCreated())
                .andDo(print());
        Vote created = TestUtil.readFromJson(action, Vote.class);
        voteNew.setId(created.getId());
        perform(doGet().basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE1, VOTE2, voteNew));
    }

    @Test
    void updateWithLocationAfter11() throws Exception {
        LocalDateTime test10_00 = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0));
        LocalDateTime test11_30 = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 30));
        Vote voteNew = new Vote(USER, RESTAURANT1, test10_00);
        Vote voteUpdated = new Vote(USER, RESTAURANT1, test11_30);
        DateTimeUtil.useFixedClockAt(test10_00);
        ResultActions action = perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(USER))
                .andExpect(status().isCreated())
                .andDo(print());
        Vote created = TestUtil.readFromJson(action, Vote.class);
        voteNew.setId(created.getId());
        perform(doGet().basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE1, VOTE2, voteNew));
        DateTimeUtil.useFixedClockAt(test11_30);
        perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(USER))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    void createNewWithWrongRestaurantId() throws Exception {
        perform(doPost("1").basicAuth(USER))
                .andExpect(status().isNotFound());
    }

    @Test
    void createNewWithLocationBefore11ByAdmin() throws Exception {
        LocalDateTime test10_00 = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0));
        Vote voteNew = new Vote(ADMIN, RESTAURANT1, test10_00);
        DateTimeUtil.useFixedClockAt(test10_00);
        ResultActions action = perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(ADMIN))
                .andExpect(status().isCreated())
                .andDo(print());
        Vote created = TestUtil.readFromJson(action, Vote.class);
        voteNew.setId(created.getId());
        perform(doGet().basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE3, VOTE4, voteNew));
    }

    @Test
    void updateWithLocationBefore11ByAdmin() throws Exception {
        LocalDateTime test10_00 = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0));
        LocalDateTime test10_30 = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 30));
        Vote voteNew = new Vote(ADMIN, RESTAURANT1, test10_00);
        Vote voteUpdated = new Vote(ADMIN, RESTAURANT1, test10_30);
        DateTimeUtil.useFixedClockAt(test10_00);
        ResultActions action = perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(ADMIN))
                .andExpect(status().isCreated())
                .andDo(print());
        Vote created = TestUtil.readFromJson(action, Vote.class);
        voteNew.setId(created.getId());
        perform(doGet().basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE3, VOTE4, voteNew));
        DateTimeUtil.useFixedClockAt(test10_30);
        ResultActions action2 = perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(ADMIN))
                .andExpect(status().isCreated())
                .andDo(print());
        Vote updated = TestUtil.readFromJson(action2, Vote.class);
        voteUpdated.setId(updated.getId());
        perform(doGet().basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE3, VOTE4, voteUpdated));
    }

    @Test
    void createNewWithLocationAfter11ByAdmin() throws Exception {
        LocalDateTime test12_00 = LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0));
        Vote voteNew = new Vote(ADMIN, RESTAURANT1, test12_00);
        DateTimeUtil.useFixedClockAt(test12_00);
        ResultActions action = perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(ADMIN))
                .andExpect(status().isCreated())
                .andDo(print());
        Vote created = TestUtil.readFromJson(action, Vote.class);
        voteNew.setId(created.getId());
        perform(doGet().basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE3, VOTE4, voteNew));
    }

    @Test
    void updateWithLocationAfter11ByAdmin() throws Exception {
        LocalDateTime test10_00 = LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0));
        LocalDateTime test11_30 = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 30));
        Vote voteNew = new Vote(ADMIN, RESTAURANT1, test10_00);
        DateTimeUtil.useFixedClockAt(test10_00);
        ResultActions action = perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(ADMIN))
                .andExpect(status().isCreated())
                .andDo(print());
        Vote created = TestUtil.readFromJson(action, Vote.class);
        voteNew.setId(created.getId());
        perform(doGet().basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_MATCHERS.contentJson(VOTE3, VOTE4, voteNew));
        DateTimeUtil.useFixedClockAt(test11_30);
        perform(doPost(String.valueOf(RESTAURANT1_ID)).basicAuth(ADMIN))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    void createNewWithWrongRestaurantIdByAdmin() throws Exception {
        perform(doPost("1").basicAuth(ADMIN))
                .andExpect(status().isNotFound());
    }
}