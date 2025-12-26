package com.epita.projet.projet_yasmine_benali.service;
import com.epita.projet.projet_yasmine_benali.dto.AuditAgenceDTO;
import com.epita.projet.projet_yasmine_benali.entities.*;
import com.epita.projet.projet_yasmine_benali.exception.ProxiBanqueException;
import com.epita.projet.projet_yasmine_benali.repository.AgenceRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class AuditService {
    private static final double SEUIL_DEBITEUR_PARTICULIER = -5000.0;
    private static final double SEUIL_DEBITEUR_ENTREPRISE = -50000.0;
    private final AgenceRepository agenceRepository;
    public AuditService(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }
    public AuditAgenceDTO auditerAgence(Long agenceId) {
        Agence agence = agenceRepository.findById(agenceId)
                .orElseThrow(() -> new ProxiBanqueException("Agence non trouvee avec l'id: " + agenceId));
        AuditAgenceDTO audit = new AuditAgenceDTO();
        audit.setAgenceId(agenceId);
        audit.setNumeroAgence(agence.getNumero());
        List<String> anomalies = new ArrayList<>();
        int nombreClients = 0;
        int nombreComptes = 0;
        if (agence.getConseillers() != null) {
            for (Conseiller conseiller : agence.getConseillers()) {
                if (conseiller.getClients() != null) {
                    for (Client client : conseiller.getClients()) {
                        nombreClients++;
                        if (client.getComptes() != null) {
                            for (Compte compte : client.getComptes()) {
                                nombreComptes++;
                                if (client.getType() == ClientType.PARTICULIER) {
                                    if (compte.getSolde() < SEUIL_DEBITEUR_PARTICULIER) {
                                        anomalies.add(String.format(
                                            "Client PARTICULIER %s %s - Compte %s debiteur de %.2f EUR (seuil: %.2f EUR)",
                                            client.getNom(), client.getPrenom(),
                                            compte.getNumero(), compte.getSolde(), SEUIL_DEBITEUR_PARTICULIER
                                        ));
                                    }
                                } else if (client.getType() == ClientType.ENTREPRISE) {
                                    if (compte.getSolde() < SEUIL_DEBITEUR_ENTREPRISE) {
                                        anomalies.add(String.format(
                                            "Client ENTREPRISE %s %s - Compte %s debiteur de %.2f EUR (seuil: %.2f EUR)",
                                            client.getNom(), client.getPrenom(),
                                            compte.getNumero(), compte.getSolde(), SEUIL_DEBITEUR_ENTREPRISE
                                        ));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        audit.setAnomalies(anomalies);
        audit.setConforme(anomalies.isEmpty());
        audit.setNombreClientsAudites(nombreClients);
        audit.setNombreComptesAudites(nombreComptes);
        return audit;
    }
    public List<AuditAgenceDTO> auditerToutesLesAgences() {
        List<AuditAgenceDTO> audits = new ArrayList<>();
        for (Agence agence : agenceRepository.findAll()) {
            audits.add(auditerAgence(agence.getId()));
        }
        return audits;
    }
}
