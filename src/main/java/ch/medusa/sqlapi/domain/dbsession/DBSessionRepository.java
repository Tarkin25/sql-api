package ch.medusa.sqlapi.domain.dbsession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBSessionRepository extends JpaRepository<DBSession, String> {

    List<DBSession> findAllByUserId(String userId);

}
