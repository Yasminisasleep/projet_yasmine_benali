package com.epita.projet.projet_yasmine_benali.service;


import com.epita.projet.projet_yasmine_benali.entities.Virement;
import com.epita.projet.projet_yasmine_benali.repository.VirementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirementService {

    private final VirementRepository virementRepository;

    public VirementService(VirementRepository virementRepository) {
        this.virementRepository = virementRepository;
    }

    public List<Virement> findAll() {
        return virementRepository.findAll();
    }

    public Virement save(Virement virement) {
        return virementRepository.save(virement);
    }
}
