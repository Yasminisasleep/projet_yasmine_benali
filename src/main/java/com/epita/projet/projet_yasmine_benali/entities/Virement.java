package com.epita.projet.projet_yasmine_benali.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Virement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Compte compteSource;

    @ManyToOne
    private Compte compteDestination;

    private double montant;
    private LocalDateTime date = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Compte getCompteSource() { return compteSource; }
    public void setCompteSource(Compte compteSource) { this.compteSource = compteSource; }

    public Compte getCompteDestination() { return compteDestination; }
    public void setCompteDestination(Compte compteDestination) { this.compteDestination = compteDestination; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
