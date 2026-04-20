package org.example.controller;

import org.example.dto.TargetStatusDTO;
import org.example.model.CheckLog;
import org.example.model.MonitoringTarget;
import org.example.repository.CheckLogRepository;
import org.example.repository.TargetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SentinelController {

    private final TargetRepository targetRepository;
    private final CheckLogRepository checkLogRepository;

    public  SentinelController(TargetRepository targetRepository, CheckLogRepository checkLogRepository) {
        this.targetRepository = targetRepository;
        this.checkLogRepository = checkLogRepository;
    }

    @GetMapping("/")
    public String getDashboard(Model model) {

        List<MonitoringTarget> targets = targetRepository.findAll();
        List<TargetStatusDTO> report = new ArrayList<>();

        for (MonitoringTarget target : targets) {

            CheckLog latestLog = checkLogRepository.findFirstBySiteNameOrderByCheckedAtDesc(target.getName())
                    .orElse(null);

            String status = (latestLog != null) ? latestLog.getStatus() : "PENDING";
            LocalDateTime time = (latestLog != null) ? latestLog.getCheckedAt() : null;

            report.add(new TargetStatusDTO(target.getId(), target.getName(), target.getUrl(), status, time));
        }

        model.addAttribute("report", report);
        return "dashboard";
    }

    @PostMapping("/add-target")
    public String addTarget(@RequestParam String name ,@RequestParam String url) {
        MonitoringTarget target = new MonitoringTarget();
        target.setName(name);
        target.setUrl(url);

        targetRepository.save(target);

        return "redirect:/";
    }

    @PostMapping("/delete-target/{id}")
    public String deleteTarget(@PathVariable Long id) {
        targetRepository.deleteById(id);
        return "redirect:/";
    }

}
