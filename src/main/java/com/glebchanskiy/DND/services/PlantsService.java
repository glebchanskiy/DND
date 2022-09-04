package com.glebchanskiy.DND.services;

import com.glebchanskiy.DND.models.Location;
import com.glebchanskiy.DND.models.Plant;
import com.glebchanskiy.DND.repositories.PlantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PlantsService {
    private final PlantsRepository plantsRepository;

    @Autowired
    public PlantsService(PlantsRepository plantsRepository) {
        this.plantsRepository = plantsRepository;
    }

    public List<Plant> findAll() {
        return plantsRepository.findAll();
    }

    public Plant findOne(int id) {
        Optional<Plant> plant = plantsRepository.findById(id);

        return plant.orElse(null);
    }

    @Transactional
    public void save(Plant location) {
        plantsRepository.save(location);
    }

    @Transactional
    public void update(int id, Plant updatedLocation) {
        updatedLocation.setId(id);
        plantsRepository.save(updatedLocation);
    }

    @Transactional
    public void delete(int id) {
        plantsRepository.deleteById(id);
    }


    public List<Plant> findRandomPlants(Location location, String rareness, int count) {

        List<Plant> plants = findAll().stream()
                .filter(plant -> plant.getLocations().contains(location) && plant.getRareness().equals(rareness))
                .collect(Collectors.toList());

        Collections.shuffle(plants);

        List<Plant> foundedPlants = new ArrayList<>();
        Random rand = new Random();

        if (!plants.isEmpty()) {
            for (int i = 0; i<count; i++)
                foundedPlants.add(plants.get(rand.nextInt(plants.size())));
        }
        return foundedPlants;
    }

    public List<Plant> findAllByNameContaining(String search) {
        return plantsRepository.findAllByNameContaining(search);
    }
}
