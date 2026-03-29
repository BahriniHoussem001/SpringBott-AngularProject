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

public class HistoriqueAchats {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	 @ManyToOne
	    @JoinColumn(name = "fournisseur_id")
	    @NotNull(message = "Le fournisseur est obligatoire")
	    private Fournisseur fournisseur;

	    @NotBlank(message = "Le produit est obligatoire")
	    private String produit;

	    @NotNull(message = "La quantité est obligatoire")
	    private Integer quantite;

	    @NotNull(message = "Le délai de livraison est obligatoire")
	    private Integer delaiLivraison;

	    public HistoriqueAchats() {
	    }

		public HistoriqueAchats(long id, @NotNull(message = "Le fournisseur est obligatoire") Fournisseur fournisseur,
				@NotBlank(message = "Le produit est obligatoire") String produit,
				@NotNull(message = "La quantité est obligatoire") Integer quantite,
				@NotNull(message = "Le délai de livraison est obligatoire") Integer delaiLivraison) {
			super();
			this.id = id;
			this.fournisseur = fournisseur;
			this.produit = produit;
			this.quantite = quantite;
			this.delaiLivraison = delaiLivraison;
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

		public Integer getDelaiLivraison() {
			return delaiLivraison;
		}

		public void setDelaiLivraison(Integer delaiLivraison) {
			this.delaiLivraison = delaiLivraison;
		}
	    

}
