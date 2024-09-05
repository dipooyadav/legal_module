package com.nscs.SBMaster.view;


import com.nscs.SBMaster.Entity.Region;
import com.nscs.SBMaster.repository.RegionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RegionMaster {

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/RegionIndex")
    public String showUserList(Model model){
        List<Region> regions = regionRepository.findAll();
        model.addAttribute( "regions",regions);
        return "Region_All";
    }


    @GetMapping("/RegionSignup")
    public String showSignUpForm(@Valid Region region, BindingResult result, Model model) {
        model.addAttribute("region", region);
        //return "add_bid_stage";
        if (result.hasErrors()) {
            return "Region_add";
        }
        return "Region_add";
    }


    @PostMapping("/addRegion")
    public String addBidStage(@Valid Region region, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Region_All";
        }

        regionRepository.save(region);
        return "redirect:/RegionIndex";
    }

    @GetMapping("/editRegionById/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Region region = regionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("region", region);

        return "Region_update";
    }

    @PostMapping("/updateRegionByid/{id}")
    public String updateBidStage(@PathVariable("id") Integer id, @Valid Region region, BindingResult result, Model model) {
        if (result.hasErrors()) {
            region.setId(id);
            return "Region_update";
        }

        regionRepository.save(region);

        return "redirect:/RegionIndex";
    }



}
