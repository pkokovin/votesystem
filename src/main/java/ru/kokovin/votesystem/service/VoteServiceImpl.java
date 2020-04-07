package ru.kokovin.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kokovin.votesystem.exception.IllegalRequestDataException;
import ru.kokovin.votesystem.exception.ResourceNotFoundException;
import ru.kokovin.votesystem.model.Vote;
import ru.kokovin.votesystem.repository.RestaurantRepository;
import ru.kokovin.votesystem.repository.UserRepository;
import ru.kokovin.votesystem.repository.VoteRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.kokovin.votesystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private final VoteRepository voteRepository;

    @Autowired
    private final RestaurantRepository restaurantRepository;

    @Autowired
    private final UserRepository userRepository;

    public VoteServiceImpl(VoteRepository voteRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Vote> get() {
        return voteRepository.findAll();
    }

    @Override
    public List<Vote> getAllByUserId(int userId) {
        return voteRepository.getAllByUserId(userId);
    }

    @Override
    public Vote getById(int id) {
        return voteRepository.getById(id)
                .orElseThrow(()-> new ResourceNotFoundException("There is no vote with id = " + id));
    }

    @Override
    public Map<String, Boolean> delete(int id) {
        voteRepository.delete(voteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vote not found : " + id)));
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public Vote create(int restaurantId, int userId, LocalDateTime current) {
        checkNotFoundWithId(restaurantRepository.existsById(restaurantId), restaurantId);
        checkNotFoundWithId(userRepository.existsById(userId), userId);
        Integer todaysUserVoteId = null;
        Vote todaysVote = voteRepository.getTodaysVoteByUserId(userId,
                LocalDateTime.of(current.toLocalDate(), LocalTime.MIN),
                LocalDateTime.of(current.toLocalDate(), LocalTime.MAX));
        if (todaysVote != null){
            if (current.toLocalTime().isAfter(LocalTime.of(11, 0))) throw
                    new IllegalRequestDataException("You can not vote twice after 11:00 AM, try again tomorrow!");
            else todaysUserVoteId = todaysVote.getId();
        }

        Vote vote = new Vote(userRepository.getOne(userId), restaurantRepository.getOne(restaurantId), current);
        if (todaysUserVoteId != null) {
            voteRepository.deleteById(todaysUserVoteId);
        }
        return voteRepository.save(vote);
    }
}
