package ch.medusa.sqlapi.domain.user.dto;

import ch.medusa.sqlapi.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface UserMapper {

    User fromDTO(UserRegistrationDTO dto);

    User fromDTO(UserDTO dto);

    UserDTO toDTO(User user);

}
