package ch.medusa.sqlapi.domain.dbsession;

import ch.medusa.sqlapi.domain.dbsession.dto.DBSessionDTO;
import ch.medusa.sqlapi.domain.dbsession.dto.DBSessionMapper;
import ch.medusa.sqlapi.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/db-sessions")
public class DBSessionController {

    private DBSessionService dbSessionService;
    private UserService userService;
    private DBSessionMapper dbSessionMapper;

    @Autowired
    public DBSessionController(DBSessionService dbSessionService, UserService userService, DBSessionMapper dbSessionMapper) {
        this.dbSessionService = dbSessionService;
        this.userService = userService;
        this.dbSessionMapper = dbSessionMapper;
    }

    @GetMapping
    public ResponseEntity<List<DBSessionDTO>> findAllByAuthenticatedUser() {
        List<DBSession> dbSessions = dbSessionService.findAllByUser(userService.findAuthenticatedUser());

        return new ResponseEntity<>(dbSessionMapper.toDTOs(dbSessions), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DBSessionDTO> findById(@PathVariable("id") String id) {
        DBSession dbSession = dbSessionService.findById(id, userService.findAuthenticatedUser());

        return new ResponseEntity<>(dbSessionMapper.toDTO(dbSession), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DBSessionDTO> save(@RequestBody @Valid DBSessionDTO dbSessionDTO) {
        DBSession dbSession = dbSessionService.saveToUser(dbSessionMapper.fromDTO(dbSessionDTO), userService.findAuthenticatedUser());

        return new ResponseEntity<>(dbSessionMapper.toDTO(dbSession), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DBSessionDTO> updateById(@PathVariable("id") String id, @RequestBody @Valid DBSessionDTO dbSessionDTO) {
        DBSession dbSession = dbSessionService.updateById(id, dbSessionMapper.fromDTO(dbSessionDTO), userService.findAuthenticatedUser());

        return new ResponseEntity<>(dbSessionMapper.toDTO(dbSession), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        dbSessionService.deleteById(id, userService.findAuthenticatedUser());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
