package com.epita.projet.projet_yasmine_benali.controller;

import com.epita.projet.projet_yasmine_benali.dto.CompteDTO;
import com.epita.projet.projet_yasmine_benali.service.CompteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<CompteDTO> getAllComptes() {
        return compteService.findAll();
    }

    @GetMapping("/{id}")
    public CompteDTO getCompteById(@PathVariable Long id) {
        return compteService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CompteDTO> createCompte(@RequestBody CompteDTO compteDTO) {
        CompteDTO created = compteService.save(compteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/client/{clientId}")
    public List<CompteDTO> getComptesByClient(@PathVariable Long clientId) {
        return compteService.findByClientId(clientId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        compteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
