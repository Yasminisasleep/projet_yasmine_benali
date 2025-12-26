package com.epita.projet.projet_yasmine_benali.controller;
import com.epita.projet.projet_yasmine_benali.dto.AgenceDTO;
import com.epita.projet.projet_yasmine_benali.service.AgenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/agences")
public class AgenceController {
    private final AgenceService agenceService;
    public AgenceController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }
    @GetMapping
    public List<AgenceDTO> getAllAgences() {
        return agenceService.findAll();
    }
    @GetMapping("/{id}")
    public AgenceDTO getAgenceById(@PathVariable Long id) {
        return agenceService.findById(id);
    }
    @PostMapping
    public ResponseEntity<AgenceDTO> createAgence(@RequestBody AgenceDTO agenceDTO) {
        AgenceDTO created = agenceService.save(agenceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgence(@PathVariable Long id) {
        agenceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
