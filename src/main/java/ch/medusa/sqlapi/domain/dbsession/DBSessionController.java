package ch.medusa.sqlapi.domain.dbsession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/credentials")
public class DBSessionController {

    private DBSessionService DBSessionService;

    @Autowired
    public DBSessionController(DBSessionService DBSessionService) {
        this.DBSessionService = DBSessionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DBSession> findById(@PathVariable("id") String id) {
        return new ResponseEntity<>(DBSessionService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DBSession> save(@RequestBody @Valid DBSession DBSession) {
        return new ResponseEntity<>(DBSessionService.save(DBSession), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DBSession> updateById(@PathVariable("id") String id, @RequestBody @Valid DBSession DBSession) {
        return new ResponseEntity<>(DBSessionService.updateById(id, DBSession), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        DBSessionService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
