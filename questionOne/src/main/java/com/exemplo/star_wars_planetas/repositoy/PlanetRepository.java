package com.exemplo.star_wars_planetas.repositoy;

import com.exemplo.star_wars_planetas.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
}


