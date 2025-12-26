package com.epita.projet.projet_yasmine_benali.mapper;
import com.epita.projet.projet_yasmine_benali.dto.VirementDTO;
import com.epita.projet.projet_yasmine_benali.entities.Virement;
import com.epita.projet.projet_yasmine_benali.repository.CompteRepository;
import org.springframework.stereotype.Component;
@Component
public class VirementMapper {
    private final CompteRepository compteRepository;
    public VirementMapper(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }
    public VirementDTO toDTO(Virement virement) {
        if (virement == null) return null;
        VirementDTO dto = new VirementDTO();
        dto.setId(virement.getId());
        dto.setMontant(virement.getMontant());
        dto.setDate(virement.getDate());
        if (virement.getCompteSource() != null) {
            dto.setCompteSourceId(virement.getCompteSource().getId());
        }
        if (virement.getCompteDestination() != null) {
            dto.setCompteDestinationId(virement.getCompteDestination().getId());
        }
        return dto;
    }
    public Virement toEntity(VirementDTO dto) {
        if (dto == null) return null;
        Virement virement = new Virement();
        virement.setId(dto.getId());
        virement.setMontant(dto.getMontant());
        if (dto.getDate() != null) {
            virement.setDate(dto.getDate());
        }
        if (dto.getCompteSourceId() != null) {
            virement.setCompteSource(compteRepository.findById(dto.getCompteSourceId()).orElse(null));
        }
        if (dto.getCompteDestinationId() != null) {
            virement.setCompteDestination(compteRepository.findById(dto.getCompteDestinationId()).orElse(null));
        }
        return virement;
    }
}
