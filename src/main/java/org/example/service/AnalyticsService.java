package org.example.service;

import org.example.model.CheckLog;
import org.example.repository.CheckLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    private final CheckLogRepository checkLogRepository;

    public AnalyticsService(CheckLogRepository checkLogRepository) {
        this.checkLogRepository = checkLogRepository;
    }

    public void printStats(String siteName){
        List<CheckLog> checkLogs = checkLogRepository.findBySiteName(siteName);
        long total = checkLogs.size();
        long errors = checkLogs.stream().filter(log -> log.getStatus().equals("DOWN")).count();

        System.out.println(String.format("=== STATS for %s: Total checks: %d, Failures: %d ===",
                siteName, total, errors));
    }

}
