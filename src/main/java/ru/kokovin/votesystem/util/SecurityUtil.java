package ru.kokovin.votesystem.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kokovin.votesystem.service.MyUserPrincipal;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {
    private SecurityUtil(){}

    public static MyUserPrincipal safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof MyUserPrincipal) ? (MyUserPrincipal ) principal : null;
    }

    public static MyUserPrincipal get() {
        MyUserPrincipal user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int authUserId() {
        return get().getId();
    }
}
