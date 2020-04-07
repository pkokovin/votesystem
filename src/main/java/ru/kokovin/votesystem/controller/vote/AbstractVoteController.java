package ru.kokovin.votesystem.controller.vote;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kokovin.votesystem.controller.AbstractController;
import ru.kokovin.votesystem.model.Vote;
import ru.kokovin.votesystem.repository.RestaurantRepository;
import ru.kokovin.votesystem.repository.UserRepository;
import ru.kokovin.votesystem.service.VoteService;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static ru.kokovin.votesystem.util.ValidationUtil.checkNotFoundWithId;

public abstract class AbstractVoteController extends AbstractController {

    @Autowired
    protected VoteService voteService;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected Clock clock;

    public List<Vote> getAll() {
        log.info("get all votes");
        return voteService.get();
    }

    public List<Vote> getAllByUserId(int userId) {
        checkNotFoundWithId(userRepository.existsById(userId), userId);
        log.info("user with id = {} get all own votes", userId);
        return voteService.getAllByUserId(userId);
    }

    public Vote getById(int id) {
        log.info("admin get vote with id {}", id);
        return voteService.getById(id);
    }

    public Map<String, Boolean> delete(int id) {
        log.info("Deleting vote with id {}", id);
        return voteService.delete(id);
    }

    public Vote create(int restaurantId, int userId, LocalDateTime localDateTime) {
        log.info("create vote of user with id {} for restaurant with id {} ", userId, restaurantId);
        return voteService.create(restaurantId, userId, localDateTime);
    }
}
