package br.com.rafaelpastor.swp.services;

import br.com.rafaelpastor.swp.entities.SwData;
import br.com.rafaelpastor.swp.repositories.SwRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class SwService {

    @Value("${SWApi.startUrl}")
    private String startUrlSWApi;

    @Value("${SWApi.endUrl}")
    private String endUrlSWApi;
    final SwRepository swRepository;

    public SwService(SwRepository swRepository) {
        this.swRepository = swRepository;
    }

    public long getCount() {
        return swRepository.count();
    }

    public List<SwData> getAll() {
        return swRepository.findAllByOrderByNameAsc();
    }

    public SwData save(SwData swData) {
        if (swData.getId().trim().equals("")) {
            swData.setId(String.valueOf(UUID.randomUUID()));
        }
        return swRepository.save(swData);
    }

    public List<SwData> findByNameContaining(String name) {
        return swRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
    }

    public SwData findById(String id) {
        return swRepository.findById(id).orElse(null);
    }

    public List<SwData> findByIdContaining(String id) {
        return swRepository.findByIdContainingIgnoreCaseOrderByNameAsc(id);
    }

    public void deleteById(String id) {
        swRepository.deleteById(id);
    }

    public void saveFirstData() {
        String urlSWApi = startUrlSWApi + "planets/" + endUrlSWApi; //external API with JSON data

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlSWApi)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Response body -> " + response.body());

            JSONObject obj = new JSONObject(response.body());
            log.info("Getting first page of API");
            JSONArray arr = obj.getJSONArray("results");
            for (int i = 0; i < arr.length(); i++) {
                SwData swData = new SwData();
                swData.setName(arr.getJSONObject(i).getString("name"));
                swData.setTerrain(arr.getJSONObject(i).getString("terrain"));
                swData.setClimate(arr.getJSONObject(i).getString("climate"));
                swData.setQuantityShowedFilms(arr.getJSONObject(i).getJSONArray("films").length());
                log.info(swData.toString());
                swRepository.save(swData);
            }
        } catch (JSONException | IOException | InterruptedException ex) {
            log.info("Could not convert JSON to data, skipping");
            log.info(ex.getMessage());
        }
    }
}
