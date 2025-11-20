package com.epita.projet.projet_yasmine_benali.entities;

import jakarta.persistence.Entity;

@Entity
public class CompteCourant extends Compte {

    private double decouvert = 1000;

    public double getDecouvert() { return decouvert; }
    public void setDecouvert(double decouvert) { this.decouvert = decouvert; }
}
