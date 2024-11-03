package com.exemplo.star_wars_planetas.service;

import com.exemplo.star_wars_planetas.entities.Planet;
import com.exemplo.star_wars_planetas.repositoy.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    public List<Planet> findAll() {
        return planetRepository.findAll();
    }

    public Optional<Planet> findById(Long id) {
        return planetRepository.findById(id);
    }

    public Planet save(Planet planet) {
        return planetRepository.save(planet);
    }

    public void deleteById(Long id) {
        planetRepository.deleteById(id);
    }
}
