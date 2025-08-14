package org.example.efinance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOCATIONS", schema = "HR")
public class Location {

    @Id
    @Column(name = "LOCATION_ID", nullable = false)
    private Long locationId;

    @Column(name = "STREET_ADDRESS", length = 40)
    private String streetAddress;


    @Column(name = "POSTAL_CODE", length = 12)
    private String postalCode;

    @Column(name = "CITY", nullable = false, length = 30)
    private String city;

    @Column(name = "STATE_PROVINCE", length = 25)
    private String stateProvince;

    @Column(name = "COUNTRY_ID", length = 2)
    private String countryId;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
}

