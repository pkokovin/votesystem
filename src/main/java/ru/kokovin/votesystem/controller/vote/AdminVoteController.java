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
import java.util.Map;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteController extends AbstractVoteController {
    static final String REST_URL = "/rest/admin/votes";

    @GetMapping
    public List<Vote> getAll() {
        return super.getAll();
    }

    @GetMapping("/user/{id}")
    public List<Vote> getUserVotesByUserId(@PathVariable(value = "id") int userId) {
        return super.getAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> getWithId(@PathVariable int id) {
        return ResponseEntity.ok().body(super.getById(id));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable int id) {
        return super.delete(id);
    }

}
