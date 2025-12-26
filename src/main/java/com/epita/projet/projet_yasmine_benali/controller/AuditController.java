package com.epita.projet.projet_yasmine_benali.controller;
import com.epita.projet.projet_yasmine_benali.dto.AuditAgenceDTO;
import com.epita.projet.projet_yasmine_benali.service.AuditService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/audit")
public class AuditController {
    private final AuditService auditService;
    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }
    @GetMapping("/agence/{agenceId}")
    public AuditAgenceDTO auditerAgence(@PathVariable Long agenceId) {
        return auditService.auditerAgence(agenceId);
    }
    @GetMapping("/agences")
    public List<AuditAgenceDTO> auditerToutesLesAgences() {
        return auditService.auditerToutesLesAgences();
    }
}
