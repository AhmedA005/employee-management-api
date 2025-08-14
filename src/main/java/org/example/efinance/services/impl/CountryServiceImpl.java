package org.example.efinance.services.impl;

import org.example.efinance.entities.Country;
import org.example.efinance.repositories.CountryRepository;
import org.example.efinance.services.CountryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Optional<Country> getCountryById(String id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country updateCountry(String id, Country country) {
        if (countryRepository.existsById(id)) {
            country.setId(id);
            return countryRepository.save(country);
        }
        return null;
    }

    @Override
    public void deleteCountryById(String id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Iterable<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
