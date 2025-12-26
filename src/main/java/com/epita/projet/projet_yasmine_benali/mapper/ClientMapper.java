package com.epita.projet.projet_yasmine_benali.mapper;
import com.epita.projet.projet_yasmine_benali.dto.ClientDTO;
import com.epita.projet.projet_yasmine_benali.entities.Client;
import com.epita.projet.projet_yasmine_benali.entities.Conseiller;
import com.epita.projet.projet_yasmine_benali.repository.ConseillerRepository;
import org.springframework.stereotype.Component;
@Component
public class ClientMapper {
    private final ConseillerRepository conseillerRepository;
    public ClientMapper(ConseillerRepository conseillerRepository) {
        this.conseillerRepository = conseillerRepository;
    }
    public ClientDTO toDTO(Client client) {
        if (client == null) return null;
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        dto.setAdresse(client.getAdresse());
        dto.setCodePostal(client.getCodePostal());
        dto.setVille(client.getVille());
        dto.setTelephone(client.getTelephone());
        dto.setType(client.getType());
        if (client.getConseiller() != null) {
            dto.setConseillerId(client.getConseiller().getId());
        }
        return dto;
    }
    public Client toEntity(ClientDTO dto) {
        if (dto == null) return null;
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setAdresse(dto.getAdresse());
        client.setCodePostal(dto.getCodePostal());
        client.setVille(dto.getVille());
        client.setTelephone(dto.getTelephone());
        client.setType(dto.getType());
        if (dto.getConseillerId() != null) {
            Conseiller conseiller = conseillerRepository.findById(dto.getConseillerId())
                    .orElse(null);
            client.setConseiller(conseiller);
        }
        return client;
    }
    public void updateEntityFromDTO(ClientDTO dto, Client client) {
        if (dto.getNom() != null) client.setNom(dto.getNom());
        if (dto.getPrenom() != null) client.setPrenom(dto.getPrenom());
        if (dto.getAdresse() != null) client.setAdresse(dto.getAdresse());
        if (dto.getCodePostal() != null) client.setCodePostal(dto.getCodePostal());
        if (dto.getVille() != null) client.setVille(dto.getVille());
        if (dto.getTelephone() != null) client.setTelephone(dto.getTelephone());
        if (dto.getType() != null) client.setType(dto.getType());
        if (dto.getConseillerId() != null) {
            Conseiller conseiller = conseillerRepository.findById(dto.getConseillerId())
                    .orElse(null);
            client.setConseiller(conseiller);
        }
    }
}
