package com.epita.projet.projet_yasmine_benali.controller;

import com.epita.projet.projet_yasmine_benali.dto.ClientDTO;
import com.epita.projet.projet_yasmine_benali.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO created = clientService.save(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.update(id, clientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/fortunes")
    public List<ClientDTO> getClientsFortunes() {
        return clientService.findClientsFortunes();
    }

    @GetMapping("/{id}/solde-total")
    public ResponseEntity<Double> getSoldeTotal(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getSoldeTotal(id));
    }
}
