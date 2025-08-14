package org.example.efinance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COUNTRIES")
public class Country {
    @Id
    @Column(name = "COUNTRY_ID", nullable = false, length = 2)
    private String id;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column(name = "REGION_ID")
    private Long regionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }
}
