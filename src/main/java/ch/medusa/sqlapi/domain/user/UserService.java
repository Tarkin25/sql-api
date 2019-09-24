package ch.medusa.sqlapi.domain.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findAuthenticatedUser();

    User updateAuthenticatedUser(User user);

    void deleteAuthenticatedUser();

    User register(User user);

    User findById(String id);

}
