package com.epita.projet.projet_yasmine_benali.service;

import com.epita.projet.projet_yasmine_benali.entities.Compte;
import com.epita.projet.projet_yasmine_benali.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService {

    private final CompteRepository compteRepository;

    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    public List<Compte> findAll() {
        return compteRepository.findAll();
    }

    public Compte save(Compte compte) {
        return compteRepository.save(compte);
    }
}
