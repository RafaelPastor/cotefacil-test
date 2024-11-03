package com.exemplo.star_wars_planetas.controller;

import com.exemplo.star_wars_planetas.entities.Planet;
import com.exemplo.star_wars_planetas.service.PlanetService;
import com.exemplo.star_wars_planetas.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.faces.bean.ViewScoped;

import java.util.List;
import java.util.Optional;

@Controller
@Scope("view")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @Autowired
    private SwapiService swapiService;

    private Planet planet = new Planet();
    private String searchName;
    private Long searchId;
    private List<Planet> planets;

    // Método para Adicionar Planeta
    public String addPlanet() {
        planetService.save(planet);
        planet = new Planet(); // Limpa o formulário após adicionar
        return "planetList.xhtml?faces-redirect=true"; // Redireciona para a página de listagem
    }

    // Método para Buscar Planetas por Nome
    public String searchByName() {
        planets = planetService.findByName(searchName);
        return null; // Atualiza a página sem redirecionar
    }

    // Método para Buscar Planetas por ID
    public String searchById() {
        Optional<Planet> planet = planetService.findById(searchId);
        if (planet.isPresent()) {
            planets = List.of(planet.get()); // Adiciona o planeta encontrado à lista
        } else {
            // Caso não tenha encontrado o planeta, você pode exibir uma mensagem de erro
            // Ou fazer outro tratamento, como redirecionar
            planets = List.of();
        }
        return null; // Atualiza a página sem redirecionar
    }

    // Método para Remover Planeta
    public String removePlanet(Long id) {
        planetService.deleteById(id);
        return "planetList.xhtml?faces-redirect=true"; // Redireciona para a página de listagem
    }

    // Método para Obter Aparições em Filmes
    public String getPlanetAppearances(String planetName) {
        return swapiService.fetchPlanetAppearances(planetName);
    }

    // Getters e Setters
    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public List<Planet> getPlanets() {
        if (planets == null) {
            planets = planetService.findAll();
        }
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }
}