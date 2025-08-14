package org.example.efinance.services.impl;

import org.example.efinance.entities.Region;
import org.example.efinance.repositories.RegionRepository;
import org.example.efinance.services.RegionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Optional<Region> getRegionById(Long id) {
        return regionRepository.findById(id);
    }

    @Override
    public Region updateRegion(Long id, Region region) {
        if (regionRepository.existsById(id)) {
            region.setId(id);
            return regionRepository.save(region);
        }
        return null;
    }

    @Override
    public void deleteRegionById(Long id) {
        regionRepository.deleteById(id);
    }

    @Override
    public Iterable<Region> getAllRegions() {
        return regionRepository.findAll();
    }
}
