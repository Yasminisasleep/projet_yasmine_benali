package com.epita.projet.projet_yasmine_benali.controller;

import com.epita.projet.projet_yasmine_benali.entities.Virement;
import com.epita.projet.projet_yasmine_benali.service.VirementService;
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
    public List<Virement> getAllVirements() {
        return virementService.findAll();
    }
}
