package org.example.efinance.services;

import org.example.efinance.entities.Country;

import java.util.Optional;

public interface CountryService {
    Country createCountry(Country country);

    Optional<Country> getCountryById(String id);

    Country updateCountry(String id, Country country);

    void deleteCountryById(String id);

    Iterable<Country> getAllCountries();
}
