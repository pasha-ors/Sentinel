package org.example.dto;

import java.time.LocalDateTime;

public class TargetStatusDTO {

    private final Long id;
    private final String name;
    private final String url;
    private final String status;
    private final LocalDateTime lastCheck;

    public TargetStatusDTO(Long id,String name, String url, String status, LocalDateTime lastCheck) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.status = status;
        this.lastCheck = lastCheck;
    }

    public Long getId() { return id; }

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
