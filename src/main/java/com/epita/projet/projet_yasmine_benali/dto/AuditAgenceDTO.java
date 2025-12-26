package com.epita.projet.projet_yasmine_benali.dto;
import java.util.List;
public class AuditAgenceDTO {
    private Long agenceId;
    private String numeroAgence;
    private boolean conforme;
    private List<String> anomalies;
    private int nombreClientsAudites;
    private int nombreComptesAudites;
    public AuditAgenceDTO() {}
    public Long getAgenceId() { return agenceId; }
    public void setAgenceId(Long agenceId) { this.agenceId = agenceId; }
    public String getNumeroAgence() { return numeroAgence; }
    public void setNumeroAgence(String numeroAgence) { this.numeroAgence = numeroAgence; }
    public boolean isConforme() { return conforme; }
    public void setConforme(boolean conforme) { this.conforme = conforme; }
    public List<String> getAnomalies() { return anomalies; }
    public void setAnomalies(List<String> anomalies) { this.anomalies = anomalies; }
    public int getNombreClientsAudites() { return nombreClientsAudites; }
    public void setNombreClientsAudites(int nombreClientsAudites) { this.nombreClientsAudites = nombreClientsAudites; }
    public int getNombreComptesAudites() { return nombreComptesAudites; }
    public void setNombreComptesAudites(int nombreComptesAudites) { this.nombreComptesAudites = nombreComptesAudites; }
}
