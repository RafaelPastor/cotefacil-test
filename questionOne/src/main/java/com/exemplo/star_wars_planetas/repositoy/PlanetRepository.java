package com.exemplo.star_wars_planetas.repositoy;

import com.exemplo.star_wars_planetas.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

    List<Planet> findByNameContaining(String name);

    Optional<Planet> findById(Long id);
}

