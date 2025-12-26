package com.epita.projet.projet_yasmine_benali.service;
import com.epita.projet.projet_yasmine_benali.dto.ConseillerDTO;
import com.epita.projet.projet_yasmine_benali.entities.Agence;
import com.epita.projet.projet_yasmine_benali.entities.Conseiller;
import com.epita.projet.projet_yasmine_benali.repository.AgenceRepository;
import com.epita.projet.projet_yasmine_benali.repository.ConseillerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ConseillerService {
    private final ConseillerRepository conseillerRepository;
    private final AgenceRepository agenceRepository;
    public ConseillerService(ConseillerRepository conseillerRepository, AgenceRepository agenceRepository) {
        this.conseillerRepository = conseillerRepository;
        this.agenceRepository = agenceRepository;
    }
    public List<ConseillerDTO> findAll() {
        return conseillerRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    public ConseillerDTO findById(Long id) {
        return conseillerRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Conseiller non trouve: " + id));
    }
    public ConseillerDTO save(ConseillerDTO dto) {
        Conseiller conseiller = new Conseiller();
        conseiller.setNom(dto.getNom());
        conseiller.setPrenom(dto.getPrenom());
        if (dto.getAgenceId() != null) {
            Agence agence = agenceRepository.findById(dto.getAgenceId())
                    .orElseThrow(() -> new RuntimeException("Agence non trouvee: " + dto.getAgenceId()));
            conseiller.setAgence(agence);
        }
        return toDTO(conseillerRepository.save(conseiller));
    }
    public void delete(Long id) {
        conseillerRepository.deleteById(id);
    }
    private ConseillerDTO toDTO(Conseiller conseiller) {
        ConseillerDTO dto = new ConseillerDTO();
        dto.setId(conseiller.getId());
        dto.setNom(conseiller.getNom());
        dto.setPrenom(conseiller.getPrenom());
        if (conseiller.getAgence() != null) {
            dto.setAgenceId(conseiller.getAgence().getId());
        }
        return dto;
    }
}
