package br.com.rafaelpastor.swp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class SwData {


    @Id
    private String id = String.valueOf(UUID.randomUUID());

    String name;
    String climate;
    String terrain;
    Integer quantityShowedFilms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public Integer getQuantityShowedFilms() {
        return quantityShowedFilms;
    }

    public void setQuantityShowedFilms(Integer quantityShowedFilms) {
        this.quantityShowedFilms = quantityShowedFilms;
    }
}
