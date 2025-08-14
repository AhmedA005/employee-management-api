package org.example.efinance.services;

import org.example.efinance.entities.Location;
import java.util.List;
import java.util.Optional;

public interface LocationService {
    public List<Location> getAllLocations();
    public Optional<Location> getLocationById(Long id);
    public Location createLocation(Location location);
    public Location updateLocation(Long id, Location updatedLocation);
    public void deleteLocation(Long id);
}
