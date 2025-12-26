package com.epita.projet.projet_yasmine_benali.service;

import com.epita.projet.projet_yasmine_benali.dto.VirementDTO;
import com.epita.projet.projet_yasmine_benali.entities.Compte;
import com.epita.projet.projet_yasmine_benali.entities.CompteCourant;
import com.epita.projet.projet_yasmine_benali.entities.Virement;
import com.epita.projet.projet_yasmine_benali.exception.ProxiBanqueException;
import com.epita.projet.projet_yasmine_benali.mapper.VirementMapper;
import com.epita.projet.projet_yasmine_benali.repository.CompteRepository;
import com.epita.projet.projet_yasmine_benali.repository.VirementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirementService {

    private final VirementRepository virementRepository;
    private final CompteRepository compteRepository;
    private final VirementMapper virementMapper;

    public VirementService(VirementRepository virementRepository,
                           CompteRepository compteRepository,
                           VirementMapper virementMapper) {
        this.virementRepository = virementRepository;
        this.compteRepository = compteRepository;
        this.virementMapper = virementMapper;
    }

    public List<VirementDTO> findAll() {
        return virementRepository.findAll().stream()
                .map(virementMapper::toDTO)
                .collect(Collectors.toList());
    }

    public VirementDTO findById(Long id) {
        Virement virement = virementRepository.findById(id)
                .orElseThrow(() -> new ProxiBanqueException("Virement non trouvé avec l'id: " + id));
        return virementMapper.toDTO(virement);
    }

    @Transactional
    public VirementDTO effectuerVirement(VirementDTO virementDTO) {
        if (virementDTO.getMontant() <= 0) {
            throw new ProxiBanqueException("Le montant du virement doit être positif");
        }

        Compte compteSource = compteRepository.findById(virementDTO.getCompteSourceId())
                .orElseThrow(() -> new ProxiBanqueException("Compte source non trouvé"));

        Compte compteDestination = compteRepository.findById(virementDTO.getCompteDestinationId())
                .orElseThrow(() -> new ProxiBanqueException("Compte destination non trouvé"));

        if (compteSource.getId().equals(compteDestination.getId())) {
            throw new ProxiBanqueException("Le compte source et destination doivent être différents");
        }

        double soldeMinAutorise = 0;
        if (compteSource instanceof CompteCourant) {
            soldeMinAutorise = -((CompteCourant) compteSource).getDecouvert();
        }

        double nouveauSoldeSource = compteSource.getSolde() - virementDTO.getMontant();
        if (nouveauSoldeSource < soldeMinAutorise) {
            throw new ProxiBanqueException("Solde insuffisant pour effectuer ce virement. " +
                    "Solde actuel: " + compteSource.getSolde() + "€, " +
                    "Découvert autorisé: " + (-soldeMinAutorise) + "€");
        }

        compteSource.setSolde(nouveauSoldeSource);
        compteDestination.setSolde(compteDestination.getSolde() + virementDTO.getMontant());

        compteRepository.save(compteSource);
        compteRepository.save(compteDestination);

        Virement virement = virementMapper.toEntity(virementDTO);
        virement.setCompteSource(compteSource);
        virement.setCompteDestination(compteDestination);

        Virement saved = virementRepository.save(virement);
        return virementMapper.toDTO(saved);
    }

    public List<VirementDTO> findByCompteId(Long compteId) {
        return virementRepository.findAll().stream()
                .filter(v -> (v.getCompteSource() != null && v.getCompteSource().getId().equals(compteId)) ||
                             (v.getCompteDestination() != null && v.getCompteDestination().getId().equals(compteId)))
                .map(virementMapper::toDTO)
                .collect(Collectors.toList());
    }
}
