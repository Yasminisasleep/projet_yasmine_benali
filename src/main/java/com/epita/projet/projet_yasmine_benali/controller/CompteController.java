package com.epita.projet.projet_yasmine_benali.controller;

import com.epita.projet.projet_yasmine_benali.entities.Compte;
import com.epita.projet.projet_yasmine_benali.service.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comptes")
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping
    public List<Compte> getAllComptes() {
        return compteService.findAll();
    }

    @PostMapping
    public Compte createCompte(@RequestBody Compte compte) {
        return compteService.save(compte);
    }
}
