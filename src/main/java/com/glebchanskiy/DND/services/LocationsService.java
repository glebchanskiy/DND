package com.glebchanskiy.DND.services;

import com.glebchanskiy.DND.models.Location;
import com.glebchanskiy.DND.repositories.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LocationsService {

    private final LocationsRepository locationsRepository;

    @Autowired
    public LocationsService(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    public List<Location> findAll() {
        return locationsRepository.findAll();
    }

    public Location findOne(int id) {
        Optional<Location> location = locationsRepository.findById(id);
        return location.orElse(null);
    }

    @Transactional
    public void save(Location location) {
        locationsRepository.save(location);
    }

    @Transactional
    public void update(int id, Location updatedLocation) {
        updatedLocation.setId(id);
        locationsRepository.save(updatedLocation);
    }

    @Transactional
    public void delete(int id) {
        locationsRepository.deleteById(id);
    }

    public List<Location> findAllByNameContaining(String search) {
        return locationsRepository.findAllByNameContaining(search);
    }
}
