package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CheckLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String siteName;
    private String status;

    private LocalDateTime checkedAt;

    public CheckLog() {}

    public CheckLog(String siteName, String status, LocalDateTime checkedAt) {
        this.siteName = siteName;
        this.status = status;
        this.checkedAt = checkedAt;
    }

    public Long getId() {
        return id;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }
}
