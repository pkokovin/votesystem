package ru.kokovin.votesystem.controller.vote;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kokovin.votesystem.model.Vote;
import ru.kokovin.votesystem.service.MyUserPrincipal;
import ru.kokovin.votesystem.util.DateTimeUtil;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = ProfileVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileVoteController extends AbstractVoteController {
    static final String REST_URL = "/rest/profile/votes";

    @GetMapping
    public List<Vote> getAllOwn(@AuthenticationPrincipal MyUserPrincipal authUser) {
        return super.getAllByUserId(authUser.getId());
    }

    @PostMapping(value = "/{restaurantId}")
    public ResponseEntity<Vote> createWithLocation(@PathVariable int restaurantId, @AuthenticationPrincipal MyUserPrincipal authUser) {
        LocalDateTime current = DateTimeUtil.current();
        Vote created = super.create(restaurantId, authUser.getId(), current);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
