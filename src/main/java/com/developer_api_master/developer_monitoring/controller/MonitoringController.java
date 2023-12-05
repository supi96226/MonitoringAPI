package com.developer_api_master.developer_monitoring.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController

@CrossOrigin("*")
public class MonitoringController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "monitorDeveloper/{id}")
    public String monitorDeveloper(@PathVariable("id") Long id) {
        String catalog_data = "";
        String location_data = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String register_url = String.format("http://localhost:8089/getRegisterDataById/%x", id);
        String developer_url = String.format("http://localhost:8088/getDeveloperData/%x", id);
        catalog_data = restTemplate.exchange(register_url, HttpMethod.GET, entity, String.class).getBody();
        location_data = restTemplate.exchange(developer_url, HttpMethod.GET, entity, String.class).getBody();
        return (catalog_data + " " + location_data);
    }
}

