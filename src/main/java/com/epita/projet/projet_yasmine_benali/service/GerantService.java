package com.epita.projet.projet_yasmine_benali.service;
import com.epita.projet.projet_yasmine_benali.dto.GerantDTO;
import com.epita.projet.projet_yasmine_benali.entities.Gerant;
import com.epita.projet.projet_yasmine_benali.repository.GerantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class GerantService {
    private final GerantRepository gerantRepository;
    public GerantService(GerantRepository gerantRepository) {
        this.gerantRepository = gerantRepository;
    }
    public List<GerantDTO> findAll() {
        return gerantRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    public GerantDTO findById(Long id) {
        return gerantRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Gerant non trouve: " + id));
    }
    public GerantDTO save(GerantDTO dto) {
        Gerant gerant = new Gerant();
        gerant.setNom(dto.getNom());
        gerant.setPrenom(dto.getPrenom());
        return toDTO(gerantRepository.save(gerant));
    }
    public void delete(Long id) {
        gerantRepository.deleteById(id);
    }
    private GerantDTO toDTO(Gerant gerant) {
        GerantDTO dto = new GerantDTO();
        dto.setId(gerant.getId());
        dto.setNom(gerant.getNom());
        dto.setPrenom(gerant.getPrenom());
        return dto;
    }
}
