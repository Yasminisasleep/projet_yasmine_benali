package com.epita.projet.projet_yasmine_benali.service;

import com.epita.projet.projet_yasmine_benali.dto.ClientDTO;
import com.epita.projet.projet_yasmine_benali.entities.*;
import com.epita.projet.projet_yasmine_benali.exception.ProxiBanqueException;
import com.epita.projet.projet_yasmine_benali.mapper.ClientMapper;
import com.epita.projet.projet_yasmine_benali.repository.CarteBancaireRepository;
import com.epita.projet.projet_yasmine_benali.repository.ClientRepository;
import com.epita.projet.projet_yasmine_benali.repository.CompteRepository;
import com.epita.projet.projet_yasmine_benali.repository.ConseillerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private static final int MAX_CLIENTS_PAR_CONSEILLER = 10;
    private static final double SEUIL_CLIENT_FORTUNE = 500000.0;

    private final ClientRepository clientRepository;
    private final CompteRepository compteRepository;
    private final CarteBancaireRepository carteBancaireRepository;
    private final ConseillerRepository conseillerRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository,
                         CompteRepository compteRepository,
                         CarteBancaireRepository carteBancaireRepository,
                         ConseillerRepository conseillerRepository,
                         ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
        this.carteBancaireRepository = carteBancaireRepository;
        this.conseillerRepository = conseillerRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ProxiBanqueException("Client non trouvé avec l'id: " + id));
        return clientMapper.toDTO(client);
    }

    @Transactional
    public ClientDTO save(ClientDTO clientDTO) {
        if (clientDTO.getConseillerId() != null) {
            Conseiller conseiller = conseillerRepository.findById(clientDTO.getConseillerId())
                    .orElseThrow(() -> new ProxiBanqueException("Conseiller non trouvé"));

            long nombreClients = clientRepository.countByConseillerId(clientDTO.getConseillerId());
            if (nombreClients >= MAX_CLIENTS_PAR_CONSEILLER) {
                throw new ProxiBanqueException("Le conseiller a déjà " + MAX_CLIENTS_PAR_CONSEILLER + " clients (maximum atteint)");
            }
        }

        Client client = clientMapper.toEntity(clientDTO);
        Client saved = clientRepository.save(client);
        return clientMapper.toDTO(saved);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ProxiBanqueException("Client non trouvé avec l'id: " + id));

        clientMapper.updateEntityFromDTO(clientDTO, client);
        Client updated = clientRepository.save(client);
        return clientMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ProxiBanqueException("Client non trouvé avec l'id: " + id));

        if (client.getCartes() != null) {
            for (CarteBancaire carte : client.getCartes()) {
                carte.setActive(false);
                carteBancaireRepository.save(carte);
            }
        }

        clientRepository.delete(client);
    }

    public List<ClientDTO> findClientsFortunes() {
        return clientRepository.findAll().stream()
                .filter(this::isClientFortune)
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean isClientFortune(Client client) {
        if (client.getComptes() == null) return false;
        double soldeTotal = client.getComptes().stream()
                .mapToDouble(Compte::getSolde)
                .sum();
        return soldeTotal > SEUIL_CLIENT_FORTUNE;
    }

    public double getSoldeTotal(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ProxiBanqueException("Client non trouvé"));

        if (client.getComptes() == null) return 0;
        return client.getComptes().stream()
                .mapToDouble(Compte::getSolde)
                .sum();
    }
}
