package org.example.efinance.controllers;

import org.example.efinance.entities.Location;
import org.example.efinance.services.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@PreAuthorize("hasAnyRole('USER','HR_MANAGER','ADMIN')")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id " + id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HR_MANAGER','ADMIN')")
    public Location createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR_MANAGER','ADMIN')")
    public Location updateLocation(@PathVariable Long id, @RequestBody Location location) {
        return locationService.updateLocation(id, location);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR_MANAGER','ADMIN')")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }
}
