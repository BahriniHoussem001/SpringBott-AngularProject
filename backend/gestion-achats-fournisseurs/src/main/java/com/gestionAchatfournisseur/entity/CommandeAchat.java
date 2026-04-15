package com.gestionAchatfournisseur.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CommandeAchat {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)

	  private long id ;
	  @ManyToOne
	    @JoinColumn(name = "fournisseur_id")
	    @NotNull(message = "Le fournisseur est obligatoire")
	    private Fournisseur fournisseur;

	    @NotNull(message = "La date est obligatoire")
	    private LocalDate date;

	    @NotNull(message = "Le statut est obligatoire")
	    private String statut;

	    @NotNull(message = "Le montant est obligatoire")
	    private Double montant;
	    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonIgnore
	    private List<LigneCommandeAchat> lignesCommande;

	    public CommandeAchat() {
	    }

		public CommandeAchat(long id, @NotNull(message = "Le fournisseur est obligatoire") Fournisseur fournisseur,
				@NotNull(message = "La date est obligatoire") LocalDate date,
				@NotNull(message = "Le statut est obligatoire") String statut,
				@NotNull(message = "Le montant est obligatoire") Double montant) {
			super();
			this.id = id;
			this.fournisseur = fournisseur;
			this.date = date;
			this.statut = statut;
			this.montant = montant;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public Fournisseur getFournisseur() {
			return fournisseur;
		}

		public void setFournisseur(Fournisseur fournisseur) {
			this.fournisseur = fournisseur;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}

		public String getStatut() {
			return statut;
		}

		public void setStatut(String statut) {
			this.statut = statut;
		}

		public Double getMontant() {
			return montant;
		}

		public void setMontant(Double montant) {
			this.montant = montant;
		}
	    
}
