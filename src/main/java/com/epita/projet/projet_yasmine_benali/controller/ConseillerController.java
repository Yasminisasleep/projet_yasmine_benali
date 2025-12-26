package com.epita.projet.projet_yasmine_benali.controller;
import com.epita.projet.projet_yasmine_benali.dto.ConseillerDTO;
import com.epita.projet.projet_yasmine_benali.service.ConseillerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/conseillers")
public class ConseillerController {
    private final ConseillerService conseillerService;
    public ConseillerController(ConseillerService conseillerService) {
        this.conseillerService = conseillerService;
    }
    @GetMapping
    public List<ConseillerDTO> getAllConseillers() {
        return conseillerService.findAll();
    }
    @GetMapping("/{id}")
    public ConseillerDTO getConseillerById(@PathVariable Long id) {
        return conseillerService.findById(id);
    }
    @PostMapping
    public ResponseEntity<ConseillerDTO> createConseiller(@RequestBody ConseillerDTO conseillerDTO) {
        ConseillerDTO created = conseillerService.save(conseillerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConseiller(@PathVariable Long id) {
        conseillerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
