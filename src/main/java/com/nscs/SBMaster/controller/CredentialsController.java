package com.nscs.SBMaster.controller;


import com.nscs.SBMaster.Entity.Credentials;
import com.nscs.SBMaster.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("credentials")
public class CredentialsController {

    @Autowired
    private CredentialsRepository credentialsRepository;


    @GetMapping("allcredentials")
    public List<Credentials> getcredentials() {
        return credentialsRepository.findAll();
    }


    @GetMapping("allcredentialsById/{id}")
    public Optional<Credentials> getcredentialsById(@PathVariable("id") int id) {
        return credentialsRepository.findById(id);
    }

    @PostMapping("/updateCredentials")
    public Credentials updateCredentials(@RequestBody Credentials updatedCredentials) {
        int id = updatedCredentials.getId(); // Extracting ID from the updated Credentials object
        Optional<Credentials> existingCredentialsOptional = credentialsRepository.findById(id);
        if (existingCredentialsOptional.isPresent()) {
            Credentials existingCredentials = existingCredentialsOptional.get();

            existingCredentials.setSince(updatedCredentials.getSince());
            existingCredentials.setCtype(updatedCredentials.getCtype());
            existingCredentials.setUpto(updatedCredentials.getUpto());
            existingCredentials.setCname(updatedCredentials.getCname());
            existingCredentials.setTech_staff(updatedCredentials.getTech_staff());
            existingCredentials.setCredentialTerm(updatedCredentials.getCredentialTerm());
            existingCredentials.setDomain(updatedCredentials.getDomain());

            existingCredentials.setNontech_staff(updatedCredentials.getNontech_staff());

            existingCredentials.setDocumentFile(updatedCredentials.getDocumentFile());
            existingCredentials.setDocumentData(updatedCredentials.getDocumentData());
            existingCredentials.setDocumentName(updatedCredentials.getDocumentName());


            return credentialsRepository.save(existingCredentials);
        } else {
            // Handle case where the competitor with the given ID does not exist
            throw new RuntimeException("Competitor with ID " + id + " not found");
        }
    }

    @PostMapping("/addCredentials")
       public List<Credentials> addCredentials(@RequestBody List<Credentials> credentialsList) {
        // You may want to add validation logic here before saving
        return credentialsRepository.saveAll(credentialsList);
    }
}