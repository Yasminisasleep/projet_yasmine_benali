package com.epita.projet.projet_yasmine_benali.service;

import com.epita.projet.projet_yasmine_benali.dto.CompteDTO;
import com.epita.projet.projet_yasmine_benali.entities.*;
import com.epita.projet.projet_yasmine_benali.exception.ProxiBanqueException;
import com.epita.projet.projet_yasmine_benali.mapper.CompteMapper;
import com.epita.projet.projet_yasmine_benali.repository.ClientRepository;
import com.epita.projet.projet_yasmine_benali.repository.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;
    private final CompteMapper compteMapper;

    public CompteService(CompteRepository compteRepository,
                         ClientRepository clientRepository,
                         CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.clientRepository = clientRepository;
        this.compteMapper = compteMapper;
    }

    public List<CompteDTO> findAll() {
        return compteRepository.findAll().stream()
                .map(compteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CompteDTO findById(Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new ProxiBanqueException("Compte non trouvé avec l'id: " + id));
        return compteMapper.toDTO(compte);
    }

    @Transactional
    public CompteDTO save(CompteDTO compteDTO) {
        if (compteDTO.getClientId() == null) {
            throw new ProxiBanqueException("Un compte doit être associé à un client");
        }

        Client client = clientRepository.findById(compteDTO.getClientId())
                .orElseThrow(() -> new ProxiBanqueException("Client non trouvé"));

        if (client.getComptes() != null) {
            boolean hasCompteCourant = client.getComptes().stream()
                    .anyMatch(c -> c instanceof CompteCourant);
            boolean hasCompteEpargne = client.getComptes().stream()
                    .anyMatch(c -> c instanceof Epargne);

            if ("COURANT".equalsIgnoreCase(compteDTO.getTypeCompte()) && hasCompteCourant) {
                throw new ProxiBanqueException("Ce client possède déjà un compte courant");
            }
            if ("EPARGNE".equalsIgnoreCase(compteDTO.getTypeCompte()) && hasCompteEpargne) {
                throw new ProxiBanqueException("Ce client possède déjà un compte épargne");
            }
        }

        Compte compte = compteMapper.toEntity(compteDTO);
        compte.setClient(client);

        Compte saved = compteRepository.save(compte);
        return compteMapper.toDTO(saved);
    }

    public List<CompteDTO> findByClientId(Long clientId) {
        return compteRepository.findAll().stream()
                .filter(c -> c.getClient() != null && c.getClient().getId().equals(clientId))
                .map(compteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new ProxiBanqueException("Compte non trouvé avec l'id: " + id));
        compteRepository.delete(compte);
    }
}
