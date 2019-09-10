package ch.medusa.sqlapi.domain.controller;

import ch.medusa.sqlapi.domain.model.Driver;
import ch.medusa.sqlapi.domain.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<List<Driver>> findAll() {
        List<Driver> drivers = driverService.findAll();

        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }
}
