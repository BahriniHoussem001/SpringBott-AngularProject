package com.gestionAchatfournisseur.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Fournisseur {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le contact est obligatoire")
    private String contact;

    @NotBlank(message = "La qualité de service est obligatoire")
    private String qualiteService;

    @NotNull(message = "La note est obligatoire")
    @Min(value = 0, message = "La note doit être supérieure ou égale à 0")
    @Max(value = 10, message = "La note doit être inférieure ou égale à 10")
    private Double note;
    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CommandeAchat> commandes;

    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<HistoriqueAchats> historiques;
    public Fournisseur() {
    }
    
    

	public Fournisseur(Long id, String nom, String contact, String qualiteService, Double note) {
		super();
		this.id = id;
		this.nom = nom;
		this.contact = contact;
		this.qualiteService = qualiteService;
		this.note = note;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getQualiteService() {
		return qualiteService;
	}

	public void setQualiteService(String qualiteService) {
		this.qualiteService = qualiteService;
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}
	public List<CommandeAchat> getCommandes() {
	    return commandes;
	}

	public void setCommandes(List<CommandeAchat> commandes) {
	    this.commandes = commandes;
	}

	public List<HistoriqueAchats> getHistoriques() {
	    return historiques;
	}

	public void setHistoriques(List<HistoriqueAchats> historiques) {
	    this.historiques = historiques;
	}
	
    

}
