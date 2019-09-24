package ch.medusa.sqlapi.domain.dbsession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public DBSession save(DBSession DBSession) {
        repository.save(DBSession);

        logger.debug("Saved credentials with new ID '{}'", DBSession.getId());
        return DBSession;
    }

    @Override
    public DBSession updateById(String id, DBSession DBSession) {
        if(repository.existsById(id)) {
            logger.debug("Found credentials with ID '{}'", id);
            DBSession.setId(id);

            logger.debug("Updated credentials with ID '{}'", id);
            return repository.save(DBSession);
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
    public DBSession findById(String id) {
        Optional<DBSession> optional = repository.findById(id);

        if (optional.isPresent()) {
            logger.debug("Found credentials with ID '{}'", id);
            return optional.get();
        } else {
            logger.debug("Credentials with ID '{}' not found", id);
            throw new NoSuchElementException();
        }
    }
}
