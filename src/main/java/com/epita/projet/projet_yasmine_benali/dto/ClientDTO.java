package com.epita.projet.projet_yasmine_benali.dto;
import com.epita.projet.projet_yasmine_benali.entities.ClientType;
public class ClientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private ClientType type;
    private Long conseillerId;
    public ClientDTO() {}
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
    public Long getConseillerId() { return conseillerId; }
    public void setConseillerId(Long conseillerId) { this.conseillerId = conseillerId; }
}
