package com.epita.projet.projet_yasmine_benali.mapper;
import com.epita.projet.projet_yasmine_benali.dto.CompteDTO;
import com.epita.projet.projet_yasmine_benali.entities.Client;
import com.epita.projet.projet_yasmine_benali.entities.Compte;
import com.epita.projet.projet_yasmine_benali.entities.CompteCourant;
import com.epita.projet.projet_yasmine_benali.entities.Epargne;
import com.epita.projet.projet_yasmine_benali.repository.ClientRepository;
import org.springframework.stereotype.Component;
@Component
public class CompteMapper {
    private final ClientRepository clientRepository;
    public CompteMapper(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public CompteDTO toDTO(Compte compte) {
        if (compte == null) return null;
        CompteDTO dto = new CompteDTO();
        dto.setId(compte.getId());
        dto.setNumero(compte.getNumero());
        dto.setSolde(compte.getSolde());
        dto.setDateOuverture(compte.getDateOuverture());
        if (compte.getClient() != null) {
            dto.setClientId(compte.getClient().getId());
        }
        if (compte instanceof CompteCourant) {
            dto.setTypeCompte("COURANT");
            dto.setDecouvert(((CompteCourant) compte).getDecouvert());
        } else if (compte instanceof Epargne) {
            dto.setTypeCompte("EPARGNE");
            dto.setTaux(((Epargne) compte).getTaux());
        }
        return dto;
    }
    public Compte toEntity(CompteDTO dto) {
        if (dto == null) return null;
        Compte compte;
        if ("COURANT".equalsIgnoreCase(dto.getTypeCompte())) {
            CompteCourant cc = new CompteCourant();
            if (dto.getDecouvert() != null) {
                cc.setDecouvert(dto.getDecouvert());
            }
            compte = cc;
        } else if ("EPARGNE".equalsIgnoreCase(dto.getTypeCompte())) {
            Epargne ep = new Epargne();
            if (dto.getTaux() != null) {
                ep.setTaux(dto.getTaux());
            }
            compte = ep;
        } else {
            throw new IllegalArgumentException("Type de compte invalide: " + dto.getTypeCompte());
        }
        compte.setId(dto.getId());
        compte.setNumero(dto.getNumero());
        compte.setSolde(dto.getSolde());
        if (dto.getDateOuverture() != null) {
            compte.setDateOuverture(dto.getDateOuverture());
        }
        if (dto.getClientId() != null) {
            Client client = clientRepository.findById(dto.getClientId()).orElse(null);
            compte.setClient(client);
        }
        return compte;
    }
}
