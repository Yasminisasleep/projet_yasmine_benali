package com.epita.projet.projet_yasmine_benali.controller;
import com.epita.projet.projet_yasmine_benali.dto.GerantDTO;
import com.epita.projet.projet_yasmine_benali.service.GerantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/gerants")
public class GerantController {
    private final GerantService gerantService;
    public GerantController(GerantService gerantService) {
        this.gerantService = gerantService;
    }
    @GetMapping
    public List<GerantDTO> getAllGerants() {
        return gerantService.findAll();
    }
    @GetMapping("/{id}")
    public GerantDTO getGerantById(@PathVariable Long id) {
        return gerantService.findById(id);
    }
    @PostMapping
    public ResponseEntity<GerantDTO> createGerant(@RequestBody GerantDTO gerantDTO) {
        GerantDTO created = gerantService.save(gerantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGerant(@PathVariable Long id) {
        gerantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
