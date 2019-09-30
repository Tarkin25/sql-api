package ch.medusa.sqlapi.domain.dbsession.dto;

import ch.medusa.sqlapi.domain.dbsession.DBSession;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel="spring", unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface DBSessionMapper {

    DBSessionDTO toDTO(DBSession dbSession);

    List<DBSessionDTO> toDTOs(List<DBSession> dbSessions);

    DBSession fromDTO(DBSessionDTO dbSessionDTO);

    List<DBSession> fromDTOs(List<DBSessionDTO> dbSessionDTOS);

}
