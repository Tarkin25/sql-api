package ch.medusa.sqlapi.domain.credentials;

public interface CredentialsService {

    Credentials save(Credentials credentials);

    Credentials updateById(String id, Credentials credentials);

    void deleteById(String id);

    Credentials findById(String id);

}
