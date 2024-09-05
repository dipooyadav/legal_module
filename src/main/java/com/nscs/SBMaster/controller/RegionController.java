package com.nscs.SBMaster.controller;



import com.nscs.SBMaster.Entity.Region;
import com.nscs.SBMaster.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Regions")
public class RegionController {

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/allregions")
    public List<Region> getRegions() {
        return regionRepository.findAll();
    }


    @GetMapping("/allRegionsById/{id}")

    public Optional<Region> getRegionById(@PathVariable int id) {
        return regionRepository.findById(id);
    }

    @PostMapping("/updateRegionName")
    public Region updateRegion(@RequestBody Region updateRegion) {
        int id = updateRegion.getId();
        Optional<Region> existingRegionOptional = regionRepository.findById(id);
        if (existingRegionOptional.isPresent()) {

            Region exstingRegion = existingRegionOptional.get();
            exstingRegion.setName(updateRegion.getName());
            return regionRepository.save(exstingRegion);
        } else {
            throw new RuntimeException("Region with ID" + "not found");
        }
    }

    @PostMapping("/addRegion")
    public List<Region> addRegion(@RequestBody List<Region> RegionList) {
        // You may want to add validation logic here before saving
        return regionRepository.saveAll(RegionList);
    }


}


