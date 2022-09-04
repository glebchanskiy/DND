package com.glebchanskiy.DND.repositories;

import com.glebchanskiy.DND.models.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantsRepository extends JpaRepository<Plant, Integer> {
    List<Plant> findAllByNameContaining(String search);
}
