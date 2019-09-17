package ch.medusa.sqlapi.domain.credentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/credentials")
public class CredentialsController {

    private CredentialsService credentialsService;

    @Autowired
    public CredentialsController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credentials> findById(@PathVariable("id") String id) {
        return new ResponseEntity<>(credentialsService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Credentials> save(@RequestBody @Valid Credentials credentials) {
        return new ResponseEntity<>(credentialsService.save(credentials), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Credentials> updateById(@PathVariable("id") String id, @RequestBody @Valid Credentials credentials) {
        return new ResponseEntity<>(credentialsService.updateById(id, credentials), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        credentialsService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
