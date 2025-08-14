package org.example.efinance.services;

import org.example.efinance.entities.Region;

import java.util.Optional;

public interface RegionService {
    Region createRegion(Region region);

    Optional<Region> getRegionById(Long id);

    Region updateRegion(Long id, Region region);

    void deleteRegionById(Long id);

    Iterable<Region> getAllRegions();
}
