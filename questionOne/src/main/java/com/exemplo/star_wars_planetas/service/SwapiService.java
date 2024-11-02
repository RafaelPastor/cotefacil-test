package com.exemplo.star_wars_planetas.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SwapiService {
    private final RestTemplate restTemplate = new RestTemplate();

    public int findByName(String planetName) {
        String url = "https://swapi.dev/api/planets/?search=" + planetName;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        JsonNode root = null;
        try {
            root = new ObjectMapper().readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return root.path("results").get(0).path("films").size();
    }
}
