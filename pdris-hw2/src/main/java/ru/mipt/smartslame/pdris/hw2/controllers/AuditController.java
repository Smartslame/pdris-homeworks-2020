package ru.mipt.smartslame.pdris.hw2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mipt.smartslame.pdris.hw2.services.AuditService;

@Controller
public class AuditController {
    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/audit")
    public String audit(@RequestParam(required = false) String userName, Model model) {
        model.addAttribute("audit", auditService.getRecords(userName));
        return "audit.html";
    }
}
