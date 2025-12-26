package com.epita.projet.projet_yasmine_benali.dto;
import java.time.LocalDateTime;
public class VirementDTO {
    private Long id;
    private Long compteSourceId;
    private Long compteDestinationId;
    private double montant;
    private LocalDateTime date;
    public VirementDTO() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCompteSourceId() { return compteSourceId; }
    public void setCompteSourceId(Long compteSourceId) { this.compteSourceId = compteSourceId; }
    public Long getCompteDestinationId() { return compteDestinationId; }
    public void setCompteDestinationId(Long compteDestinationId) { this.compteDestinationId = compteDestinationId; }
    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
