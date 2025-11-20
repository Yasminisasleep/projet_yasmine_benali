package com.epita.projet.projet_yasmine_benali.entities;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;

    @Enumerated(EnumType.STRING)
    private ClientType type;

    @ManyToOne
    private Conseiller conseiller;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Compte> comptes;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<CarteBancaire> cartes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public ClientType getType() { return type; }
    public void setType(ClientType type) { this.type = type; }

    public Conseiller getConseiller() { return conseiller; }
    public void setConseiller(Conseiller conseiller) { this.conseiller = conseiller; }

    public List<Compte> getComptes() { return comptes; }
    public void setComptes(List<Compte> comptes) { this.comptes = comptes; }

    public List<CarteBancaire> getCartes() { return cartes; }
    public void setCartes(List<CarteBancaire> cartes) { this.cartes = cartes; }
}
