package com.glebchanskiy.DND.repositories;

import com.glebchanskiy.DND.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationsRepository extends JpaRepository<Location, Integer> {
    List<Location> findAllByNameContaining(String search);
}
