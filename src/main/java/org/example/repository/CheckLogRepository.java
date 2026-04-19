package org.example.repository;

import org.example.model.CheckLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckLogRepository extends JpaRepository<CheckLog, Long> {
    List<CheckLog> findBySiteName(String siteName);
    List<CheckLog> findBySiteNameAndStatus(String siteName, String status);
}
