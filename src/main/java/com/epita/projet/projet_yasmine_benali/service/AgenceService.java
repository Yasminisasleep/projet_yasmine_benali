package com.epita.projet.projet_yasmine_benali.service;
import com.epita.projet.projet_yasmine_benali.dto.AgenceDTO;
import com.epita.projet.projet_yasmine_benali.entities.Agence;
import com.epita.projet.projet_yasmine_benali.entities.Gerant;
import com.epita.projet.projet_yasmine_benali.repository.AgenceRepository;
import com.epita.projet.projet_yasmine_benali.repository.GerantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AgenceService {
    private final AgenceRepository agenceRepository;
    private final GerantRepository gerantRepository;
    public AgenceService(AgenceRepository agenceRepository, GerantRepository gerantRepository) {
        this.agenceRepository = agenceRepository;
        this.gerantRepository = gerantRepository;
    }
    public List<AgenceDTO> findAll() {
        return agenceRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    public AgenceDTO findById(Long id) {
        return agenceRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Agence non trouvee: " + id));
    }
    public AgenceDTO save(AgenceDTO dto) {
        Agence agence = new Agence();
        agence.setNumero(dto.getNumero());
        agence.setDateCreation(dto.getDateCreation());
        if (dto.getGerantId() != null) {
            Gerant gerant = gerantRepository.findById(dto.getGerantId())
                    .orElseThrow(() -> new RuntimeException("Gerant non trouve: " + dto.getGerantId()));
            agence.setGerant(gerant);
        }
        return toDTO(agenceRepository.save(agence));
    }
    public void delete(Long id) {
        agenceRepository.deleteById(id);
    }
    private AgenceDTO toDTO(Agence agence) {
        AgenceDTO dto = new AgenceDTO();
        dto.setId(agence.getId());
        dto.setNumero(agence.getNumero());
        dto.setDateCreation(agence.getDateCreation());
        if (agence.getGerant() != null) {
            dto.setGerantId(agence.getGerant().getId());
        }
        return dto;
    }
}
