package ch.medusa.sqlapi.domain.dbsession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBSessionRepository extends JpaRepository<DBSession, String> {



}
