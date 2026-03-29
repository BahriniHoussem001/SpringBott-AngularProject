package com.gestionAchatfournisseur.entity;

public class EvaluationFournisseur {
	 private Long id;
	    private String nom;
	    private Double note;
	    private String qualiteService;
	    private Double delaiMoyenLivraison;
	    private String efficacite;

	    public EvaluationFournisseur() {
	    }

		public EvaluationFournisseur(Long id, String nom, Double note, String qualiteService,
				Double delaiMoyenLivraison, String efficacite) {
			super();
			this.id = id;
			this.nom = nom;
			this.note = note;
			this.qualiteService = qualiteService;
			this.delaiMoyenLivraison = delaiMoyenLivraison;
			this.efficacite = efficacite;
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

		public Double getNote() {
			return note;
		}

		public void setNote(Double note) {
			this.note = note;
		}

		public String getQualiteService() {
			return qualiteService;
		}

		public void setQualiteService(String qualiteService) {
			this.qualiteService = qualiteService;
		}

		public Double getDelaiMoyenLivraison() {
			return delaiMoyenLivraison;
		}

		public void setDelaiMoyenLivraison(Double delaiMoyenLivraison) {
			this.delaiMoyenLivraison = delaiMoyenLivraison;
		}

		public String getEfficacite() {
			return efficacite;
		}

		public void setEfficacite(String efficacite) {
			this.efficacite = efficacite;
		}
	    

}
