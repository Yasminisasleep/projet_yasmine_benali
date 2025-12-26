package com.epita.projet.projet_yasmine_benali.dto;
public class AgenceDTO {
    private Long id;
    private String numero;
    private String dateCreation;
    private Long gerantId;
    public AgenceDTO() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getDateCreation() { return dateCreation; }
    public void setDateCreation(String dateCreation) { this.dateCreation = dateCreation; }
    public Long getGerantId() { return gerantId; }
    public void setGerantId(Long gerantId) { this.gerantId = gerantId; }
}
