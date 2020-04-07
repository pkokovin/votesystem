package ru.kokovin.votesystem.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.kokovin.votesystem.model.User;

public class UserUtil {
    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
