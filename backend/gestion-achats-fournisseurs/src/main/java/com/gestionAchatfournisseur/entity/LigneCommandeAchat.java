package com.gestionAchatfournisseur.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class LigneCommandeAchat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	 @ManyToOne
	    @JoinColumn(name = "commande_id")
	    @NotNull(message = "La commande est obligatoire")
	    private CommandeAchat commande;

	    @NotBlank(message = "Le produit est obligatoire")
	    private String produit;

	    @NotNull(message = "La quantité est obligatoire")
	    private Integer quantite;

	    @NotNull(message = "Le prix unitaire est obligatoire")
	    private Double prixUnitaire;

	    public LigneCommandeAchat() {
	    }

		public LigneCommandeAchat(long id, @NotNull(message = "La commande est obligatoire") CommandeAchat commande,
				@NotBlank(message = "Le produit est obligatoire") String produit,
				@NotNull(message = "La quantité est obligatoire") Integer quantite,
				@NotNull(message = "Le prix unitaire est obligatoire") Double prixUnitaire) {
			super();
			this.id = id;
			this.commande = commande;
			this.produit = produit;
			this.quantite = quantite;
			this.prixUnitaire = prixUnitaire;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public CommandeAchat getCommande() {
			return commande;
		}

		public void setCommande(CommandeAchat commande) {
			this.commande = commande;
		}

		public String getProduit() {
			return produit;
		}

		public void setProduit(String produit) {
			this.produit = produit;
		}

		public Integer getQuantite() {
			return quantite;
		}

		public void setQuantite(Integer quantite) {
			this.quantite = quantite;
		}

		public Double getPrixUnitaire() {
			return prixUnitaire;
		}

		public void setPrixUnitaire(Double prixUnitaire) {
			this.prixUnitaire = prixUnitaire;
		}
	    

	
	
	
}
