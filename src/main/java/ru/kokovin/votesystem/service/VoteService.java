package ru.kokovin.votesystem.service;

import ru.kokovin.votesystem.model.Vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface VoteService {
    List<Vote> get();

    List<Vote> getAllByUserId(int userId);

    Vote getById(int id);

    Map<String, Boolean> delete(int id);

    Vote create(int restaurantId, int userId, LocalDateTime localDateTime);
}
