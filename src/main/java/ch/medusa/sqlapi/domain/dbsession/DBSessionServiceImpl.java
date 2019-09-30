package ch.medusa.sqlapi.domain.dbsession;

import ch.medusa.sqlapi.config.exception.PermissionDeniedException;
import ch.medusa.sqlapi.domain.user.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DBSessionServiceImpl implements DBSessionService {

    private Logger logger;
    private DBSessionRepository repository;

    @Autowired
    public DBSessionServiceImpl(Logger logger, DBSessionRepository repository) {
        this.logger = logger;
        this.repository = repository;
    }

    @Override
    public List<DBSession> findAllByUser(User user) {
        logger.debug("Loaded all DBSessions for User with ID '{}'", user.getId());
        return repository.findAllByUserId(user.getId());
    }

    @Override
    public DBSession saveToUser(DBSession dbSession, User user) {
        dbSession.setUser(user);
        dbSession.setId(null);
        repository.save(dbSession);

        logger.debug("Saved DBSession with new ID '{}' to User with ID '{}'", dbSession.getId(), user.getId());
        return dbSession;
    }

    @Override
    public DBSession updateById(String id, DBSession dbSession, User user) {
        checkPossession(id, user);

        if(repository.existsById(id)) {
            logger.debug("Found DBSession with ID '{}'", id);
            dbSession.setId(id);
            dbSession.setUser(user);

            logger.debug("Updated DBSession with ID '{}'", id);
            return repository.save(dbSession);
        } else {
            logger.debug("DBSession with ID '{}' not found", id);
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(String id, User user) {
        checkPossession(id, user);

        if(repository.existsById(id)) {
            logger.debug("Deleted DBSession with ID '{}'", id);
            repository.deleteById(id);
        } else {
            logger.debug("DBSession with ID '{}' not found", id);
            throw new NoSuchElementException();
        }
    }

    @Override
    public DBSession findById(String id, User user) {
        checkPossession(id, user);

        return findByIdInternal(id);
    }

    private DBSession findByIdInternal(String id) {
        Optional<DBSession> optional = repository.findById(id);

        if (optional.isPresent()) {
            logger.debug("Found DBSession with ID '{}'", id);
            return optional.get();
        } else {
            logger.debug("DBSession with ID '{}' not found", id);
            throw new NoSuchElementException();
        }
    }

    private void checkPossession(String dbSessionId, User user) {
        DBSession dbSession = findByIdInternal(dbSessionId);

        if(!dbSession.getUser().getId().equals(user.getId())) {
            logger.debug("DBSession with ID '{}' belongs to User with ID '{}' while authenticated User's ID is '{}'", dbSessionId, dbSession.getUser().getId(), user.getId());
            throw new PermissionDeniedException();
        }
    }
}
