package ch.medusa.sqlapi.domain.credentials;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CredentialsServiceImpl implements CredentialsService {

    private Logger logger;
    private CredentialsRepository repository;

    @Autowired
    public CredentialsServiceImpl(Logger logger, CredentialsRepository repository) {
        this.logger = logger;
        this.repository = repository;
    }

    @Override
    public Credentials save(Credentials credentials) {
        repository.save(credentials);

        logger.debug("Saved credentials with new ID '{}'", credentials.getId());
        return credentials;
    }

    @Override
    public Credentials updateById(String id, Credentials credentials) {
        if(repository.existsById(id)) {
            logger.debug("Found credentials with ID '{}'", id);
            credentials.setId(id);

            logger.debug("Updated credentials with ID '{}'", id);
            return repository.save(credentials);
        } else {
            logger.debug("Credentials with ID '{}' not found", id);
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(String id) {
        if(repository.existsById(id)) {
            logger.debug("Deleted credentials with ID '{}'", id);

            repository.deleteById(id);
        } else {
            logger.debug("Credentials with ID '{}' not found", id);
            throw new NoSuchElementException();
        }
    }

    @Override
    public Credentials findById(String id) {
        Optional<Credentials> optional = repository.findById(id);

        if (optional.isPresent()) {
            logger.debug("Found credentials with ID '{}'", id);
            return optional.get();
        } else {
            logger.debug("Credentials with ID '{}' not found", id);
            throw new NoSuchElementException();
        }
    }
}
