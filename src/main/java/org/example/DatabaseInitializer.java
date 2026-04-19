package org.example;

import jakarta.annotation.PostConstruct;
import org.example.model.MonitoringTarget;
import org.example.repository.TargetRepository;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    private final TargetRepository targetRepository;

    public DatabaseInitializer(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @PostConstruct
    public void init(){
        System.out.println(">>> Initializing database with targets...");
        targetRepository.save(new MonitoringTarget("Google", "https://google.com"));
        targetRepository.save(new MonitoringTarget("GitHub", "https://github.com"));
        targetRepository.save(new MonitoringTarget("Invalid Site", "https://this-site-does-not-exist.test"));
    }
}
