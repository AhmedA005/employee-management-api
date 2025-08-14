package org.example.efinance.services.impl;

import org.example.efinance.entities.Location;
import org.example.efinance.repositories.LocationRepository;
import org.example.efinance.services.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, Location updatedLocation) {
        return locationRepository.findById(id)
                .map(location -> {
                    location.setStreetAddress(updatedLocation.getStreetAddress());
                    location.setPostalCode(updatedLocation.getPostalCode());
                    location.setCity(updatedLocation.getCity());
                    location.setStateProvince(updatedLocation.getStateProvince());
                    location.setCountryId(updatedLocation.getCountryId());
                    return locationRepository.save(location);
                })
                .orElseThrow(() -> new RuntimeException("Location not found with id " + id));
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
