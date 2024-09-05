package com.nscs.SBMaster.controller;

import com.nscs.SBMaster.model.Tender;
import com.nscs.SBMaster.repository.TenderRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tender")
public class TenderController {
    @Autowired
    private TenderRepository tenderRepository ;

    @GetMapping("/alltender")
    public List<Tender> getAllTender() {
        return tenderRepository.findAll() ;
    }


    @GetMapping("/gettender/{id}")
    public Tender showUpdateForm(@PathVariable("id") long id) {
        Tender user = tenderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid tender Id:" + id));
        return user;
    }



}
