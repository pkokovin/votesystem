package ru.kokovin.votesystem.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.kokovin.votesystem.model.User;
import ru.kokovin.votesystem.service.MyUserPrincipal;

import javax.validation.Valid;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping
    public ResponseEntity<User> get(@AuthenticationPrincipal MyUserPrincipal authUser) {
        return ResponseEntity.ok().body(super.getById(authUser.getId()));
    }

    @DeleteMapping
    public Map<String, Boolean> delete(@AuthenticationPrincipal MyUserPrincipal authUser) {
        return super.delete(authUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update(@Valid @RequestBody User userDetails, @AuthenticationPrincipal MyUserPrincipal authUser) {
        return ResponseEntity.ok(super.update(userDetails, authUser.getId()));
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        User created = super.reg(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
