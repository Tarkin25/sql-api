package ch.medusa.sqlapi.domain.service;

import ch.medusa.sqlapi.domain.model.Driver;
import ch.medusa.sqlapi.domain.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private DriverRepository repository;

    @Autowired
    public DriverService(DriverRepository repository) {
        this.repository = repository;
    }

    public List<Driver> findAll() {
        return repository.findAll();
    }
}
