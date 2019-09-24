package ch.medusa.sqlapi.domain.dbsession;

import ch.medusa.sqlapi.domain.user.User;

public interface DBSessionService {

    DBSession saveToUser(DBSession DBSession, User user);

    DBSession updateById(String id, DBSession DBSession, User user);

    void deleteById(String id);

    DBSession findById(String id);

}
