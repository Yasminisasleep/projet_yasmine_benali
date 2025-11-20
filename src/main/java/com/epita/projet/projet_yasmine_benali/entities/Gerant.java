package com.epita.projet.projet_yasmine_benali.entities;


import jakarta.persistence.*;

@Entity
public class Gerant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @OneToOne(mappedBy = "gerant")
    private Agence agence;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public Agence getAgence() { return agence; }
    public void setAgence(Agence agence) { this.agence = agence; }
}
