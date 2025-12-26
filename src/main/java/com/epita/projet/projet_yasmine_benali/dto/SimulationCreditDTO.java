package com.epita.projet.projet_yasmine_benali.dto;
public class SimulationCreditDTO {
    private double montant;
    private int dureeEnMois;
    private double tauxAnnuel;
    private String typeCredit;
    private double mensualite;
    private double coutTotal;
    private double coutInterets;
    public SimulationCreditDTO() {}
    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
    public int getDureeEnMois() { return dureeEnMois; }
    public void setDureeEnMois(int dureeEnMois) { this.dureeEnMois = dureeEnMois; }
    public double getTauxAnnuel() { return tauxAnnuel; }
    public void setTauxAnnuel(double tauxAnnuel) { this.tauxAnnuel = tauxAnnuel; }
    public String getTypeCredit() { return typeCredit; }
    public void setTypeCredit(String typeCredit) { this.typeCredit = typeCredit; }
    public double getMensualite() { return mensualite; }
    public void setMensualite(double mensualite) { this.mensualite = mensualite; }
    public double getCoutTotal() { return coutTotal; }
    public void setCoutTotal(double coutTotal) { this.coutTotal = coutTotal; }
    public double getCoutInterets() { return coutInterets; }
    public void setCoutInterets(double coutInterets) { this.coutInterets = coutInterets; }
}
