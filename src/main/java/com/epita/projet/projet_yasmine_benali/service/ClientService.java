package com.epita.projet.projet_yasmine_benali.service;

import com.epita.projet.projet_yasmine_benali.entities.Client;
import com.epita.projet.projet_yasmine_benali.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
