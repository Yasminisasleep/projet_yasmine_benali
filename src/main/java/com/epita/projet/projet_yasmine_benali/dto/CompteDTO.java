package com.epita.projet.projet_yasmine_benali.dto;
import java.time.LocalDate;
public class CompteDTO {
    private Long id;
    private String numero;
    private double solde;
    private LocalDate dateOuverture;
    private Long clientId;
    private String typeCompte;
    private Double decouvert;
    private Double taux;
    public CompteDTO() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
    public LocalDate getDateOuverture() { return dateOuverture; }
    public void setDateOuverture(LocalDate dateOuverture) { this.dateOuverture = dateOuverture; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public String getTypeCompte() { return typeCompte; }
    public void setTypeCompte(String typeCompte) { this.typeCompte = typeCompte; }
    public Double getDecouvert() { return decouvert; }
    public void setDecouvert(Double decouvert) { this.decouvert = decouvert; }
    public Double getTaux() { return taux; }
    public void setTaux(Double taux) { this.taux = taux; }
}
