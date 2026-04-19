package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.CheckLog;
import org.example.model.MonitoringTarget;
import org.example.repository.CheckLogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CheckService {

    public final RestTemplate restTemplate;
    public final CheckLogRepository checkLogRepository;

    public CheckService(RestTemplate restTemplate, CheckLogRepository checkLogRepository) {
        this.restTemplate = restTemplate;
        this.checkLogRepository = checkLogRepository;
    }

    @Transactional
    public void check(MonitoringTarget target){
        try {

            long start = System.currentTimeMillis();

            ResponseEntity<String> response = restTemplate.getForEntity(target.getUrl(), String.class);

            long duration = System.currentTimeMillis() - start;

            checkLogRepository.save(new CheckLog(target.getName(), "UP", LocalDateTime.now()));

            System.out.println(String.format(">>> [SENTINEL] %s | Status: %s | Time: %dms",
                    target.getName(), response.getStatusCode(), duration));

        }catch(Exception e){

            checkLogRepository.save(new CheckLog(target.getName(), "DOWN", LocalDateTime.now()));

            System.out.println(">>> [SENTINEL] " + target.getName() + " is DOWN! Error: " + e.getMessage());

        }
    }
}
