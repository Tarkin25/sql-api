package ch.medusa.sqlapi.domain.user;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger;
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(Logger logger, UserRepository repository, PasswordEncoder passwordEncoder) {
        this.logger = logger;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User findById(String id) {
        Optional<User> optional = repository.findById(id);

        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public User updateAuthenticatedUser(User user) {
        return null;
    }

    @Override
    public void deleteAuthenticatedUser() {

    }

    @Override
    public User register(User user) {
        if(repository.existsByUsername(user.getUsername())) {
            logger.debug("Attempt to register new user failed: username '{}' is already in use", user.getUsername());
            throw new IllegalArgumentException(String.format("Username '%s' is not available", user.getUsername()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.debug("Encoded new user's password");

        logger.debug("Registered new user with username '{}'", user.getUsername());
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = repository.findByUsername(username);

        if(optional.isPresent()) {
            logger.debug("Loaded user by username '{}'", username);
            return optional.get();
        } else {
            logger.debug("User with username '{}' not found", username);
            throw new UsernameNotFoundException(String.format("User with username '%s' not found", username));
        }
    }
}
