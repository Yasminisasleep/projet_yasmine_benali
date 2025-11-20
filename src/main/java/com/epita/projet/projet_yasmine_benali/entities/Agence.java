package com.epita.projet.projet_yasmine_benali.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String dateCreation;

    @OneToOne
    private Gerant gerant;

    @OneToMany(mappedBy = "agence")
    private List<Conseiller> conseillers;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getDateCreation() { return dateCreation; }
    public void setDateCreation(String dateCreation) { this.dateCreation = dateCreation; }
    public Gerant getGerant() { return gerant; }
    public void setGerant(Gerant gerant) { this.gerant = gerant; }
    public List<Conseiller> getConseillers() { return conseillers; }
    public void setConseillers(List<Conseiller> conseillers) { this.conseillers = conseillers; }
}
