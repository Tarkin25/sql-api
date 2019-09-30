package ch.medusa.sqlapi.domain.dbsession;

import ch.medusa.sqlapi.domain.user.User;

import java.util.List;

public interface DBSessionService {

    List<DBSession> findAllByUser(User user);

    DBSession saveToUser(DBSession DBSession, User user);

    DBSession updateById(String id, DBSession DBSession, User user);

    void deleteById(String id, User user);

    DBSession findById(String id, User user);
}
