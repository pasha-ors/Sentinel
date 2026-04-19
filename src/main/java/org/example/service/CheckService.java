package org.example.service;

import org.example.model.MonitoringTarget;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CheckService {

    public final RestTemplate restTemplate;

    public CheckService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public void check(MonitoringTarget target){
        try {
            long start = System.currentTimeMillis();

            ResponseEntity<String> response = restTemplate.getForEntity(target.getUrl(), String.class);

            long duration = System.currentTimeMillis() - start;

            System.out.println(String.format(">>> [SENTINEL] %s | Status: %s | Time: %dms",
                    target.getName(), response.getStatusCode(), duration));
        }catch(Exception e){
            System.out.println(">>> [SENTINEL] " + target.getName() + " is DOWN! Error: " + e.getMessage());
        }
    }
}
