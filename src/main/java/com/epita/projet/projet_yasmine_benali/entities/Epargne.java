package com.epita.projet.projet_yasmine_benali.entities;

import jakarta.persistence.Entity;

@Entity
public class Epargne extends Compte {

    private double taux = 0.03;

    public double getTaux() { return taux; }
    public void setTaux(double taux) { this.taux = taux; }
}
