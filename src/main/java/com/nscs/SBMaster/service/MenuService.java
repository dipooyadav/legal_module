package com.nscs.SBMaster.service;

import com.nscs.SBMaster.model.MenuItem;
import com.nscs.SBMaster.model.MenuResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MenuService {

    private final RestTemplate restTemplate;

    public MenuService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MenuResponse  getUserMenuData(int userId) {
        String url = "http://localhost:8080/Master/menu/userLoginMenus/" + userId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);

        // Make the POST request and expect MenuResponse
        ResponseEntity<MenuResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                MenuResponse.class
        );

        return response.getBody();
    }
}


