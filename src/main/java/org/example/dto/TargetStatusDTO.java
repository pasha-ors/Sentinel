package org.example.dto;

import java.time.LocalDateTime;

public class TargetStatusDTO {

    private String name;
    private String url;
    private String status;
    private LocalDateTime lastCheck;

    public TargetStatusDTO(String name, String url, String status, LocalDateTime lastCheck) {
        this.name = name;
        this.url = url;
        this.status = status;
        this.lastCheck = lastCheck;
    }

    public LocalDateTime getLastCheck() {
        return lastCheck;
    }

    public String getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
