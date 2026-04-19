package org.example;

import org.example.model.MonitoringTarget;
import org.example.repository.TargetRepository;
import org.example.service.AnalyticsService;
import org.example.service.CheckService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MonitoringEngine {

    public final CheckService checkService;
    public final AnalyticsService analyticsService;
    public final TargetRepository targetRepository;

    public MonitoringEngine(CheckService checkService,
                            TargetRepository targetRepository,
                            AnalyticsService analyticsService
    ){
        this.checkService = checkService;
        this.targetRepository = targetRepository;
        this.analyticsService = analyticsService;
    }

    @Scheduled(fixedRateString = "${monitoring.rate}")
    public void startMonitoring(){

        List<MonitoringTarget> targets = targetRepository.findAll();
        System.out.println("--- Starting monitoring cycle for " + targets.size() + " targets ---");

        for (MonitoringTarget target : targets) {
            checkService.check(target);
        }
    }

    @Scheduled(fixedRateString = "${monitoring.rate}")
    public void startAnalytics(){

        List<MonitoringTarget> targets = targetRepository.findAll();
        System.out.println("--- Starting analytics cycle for " + targets.size() + " targets ---");

        for  (MonitoringTarget target : targets) {
            analyticsService.printStats(target.getName());
        }
    }
}


