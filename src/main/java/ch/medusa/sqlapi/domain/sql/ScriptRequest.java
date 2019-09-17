package ch.medusa.sqlapi.domain.sql;

import ch.medusa.sqlapi.domain.credentials.Credentials;
import org.springframework.web.multipart.MultipartFile;

public class ScriptRequest {

    private Credentials credentials;
    private MultipartFile file;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
