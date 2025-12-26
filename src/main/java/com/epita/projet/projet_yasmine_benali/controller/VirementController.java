package com.epita.projet.projet_yasmine_benali.controller;

import com.epita.projet.projet_yasmine_benali.dto.VirementDTO;
import com.epita.projet.projet_yasmine_benali.service.VirementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/virements")
public class VirementController {

    private final VirementService virementService;

    public VirementController(VirementService virementService) {
        this.virementService = virementService;
    }

    @GetMapping
    public List<VirementDTO> getAllVirements() {
        return virementService.findAll();
    }

    @GetMapping("/{id}")
    public VirementDTO getVirementById(@PathVariable Long id) {
        return virementService.findById(id);
    }

    @PostMapping
    public ResponseEntity<VirementDTO> effectuerVirement(@RequestBody VirementDTO virementDTO) {
        VirementDTO result = virementService.effectuerVirement(virementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/compte/{compteId}")
    public List<VirementDTO> getVirementsByCompte(@PathVariable Long compteId) {
        return virementService.findByCompteId(compteId);
    }
}
