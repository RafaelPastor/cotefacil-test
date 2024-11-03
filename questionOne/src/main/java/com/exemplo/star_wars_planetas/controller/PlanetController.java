package com.exemplo.star_wars_planetas.controller;

import com.exemplo.star_wars_planetas.entities.Planet;
import com.exemplo.star_wars_planetas.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @GetMapping
    public List<Planet> getAllPlanets() {
        return planetService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable Long id) {
        return planetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet planet) {
        Planet savedPlanet = planetService.save(planet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlanet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Planet> updatePlanet(@PathVariable Long id, @RequestBody Planet planet) {
        return planetService.findById(id)
                .map(existingPlanet -> {
                    existingPlanet.setName(planet.getName());
                    existingPlanet.setClimate(planet.getClimate());
                    existingPlanet.setTerrain(planet.getTerrain());
                    Planet updatedPlanet = planetService.save(existingPlanet);
                    return ResponseEntity.ok(updatedPlanet);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanet(@PathVariable Long id) {
        if (planetService.findById(id).isPresent()) {
            planetService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
