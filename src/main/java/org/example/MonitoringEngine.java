package org.example;

import org.example.model.MonitoringTarget;
import org.example.service.CheckService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonitoringEngine {

    public final CheckService checkService;

    public MonitoringEngine(CheckService checkService){
        this.checkService = checkService;
    }

    @Scheduled(fixedRate = 5000)
    public void startMonitoring(){
        checkService.check(new MonitoringTarget("Google", "https://google.com"));
        checkService.check(new MonitoringTarget("FakeSite", "https://not-existing-site-123.com"));
    }
}


