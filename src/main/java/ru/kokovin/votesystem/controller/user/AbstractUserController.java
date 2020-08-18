package ru.kokovin.votesystem.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kokovin.votesystem.controller.AbstractController;
import ru.kokovin.votesystem.exception.ResourceNotFoundException;
import ru.kokovin.votesystem.model.Role;
import ru.kokovin.votesystem.model.User;
import ru.kokovin.votesystem.repository.UserRepository;
import ru.kokovin.votesystem.service.MyUserDetailService;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.kokovin.votesystem.util.ValidationUtil.assureIdConsistent;
import static ru.kokovin.votesystem.util.ValidationUtil.checkNew;

public abstract class AbstractUserController extends AbstractController {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    MyUserDetailService userService;

    public List<User> getAll() {
        log.info("get all users");
        return userService.getAll();
    }

    public User getById(int userId) {
        log.info("get user with id = " + userId);
        return userService.get(userId);
    }

    public Map<String, Boolean> delete(int userId) {
        log.info("try to delete user with id : " + userId);
        return userService.delete(userId);
    }

    public User update(User userDetails, int userId) {
        log.info("try to update user {} with id : {}", userDetails, userId);
        assureIdConsistent(userDetails, userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found : " + userId));
        user.setName(userDetails.getName() == null ? user.getName() : userDetails.getName());
        user.setEmail(userDetails.getEmail() == null ? user.getEmail() : userDetails.getEmail());
        user.setEnabled(userDetails.isEnabled());
        user.setPassword(userDetails.getPassword() == null ? user.getPassword() : userDetails.getPassword());
        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            user.setRoles(userDetails.getRoles() == null ? user.getRoles() : userDetails.getRoles());
        } else {
            user.setRoles(Set.of(Role.ROLE_USER));
        }
        return userService.update(user);
    }

    public User save(User user) {
        checkNew(user);
        log.info("create new user " + user.getName() + ": " + user.getEmail());
        return userService.create(user);
    }

    public User reg(User user) {
        checkNew(user);
        user.setRoles(Set.of(Role.ROLE_USER));
        log.info("create new user " + user.getName() + ": " + user.getEmail());
        return userService.create(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return userService.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        userService.enable(id, enabled);
    }
}
