package com.gestionAchatfournisseur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestionAchatfournisseur.entity.LigneCommandeAchat;
import com.gestionAchatfournisseur.repo.LigneCommandeAchatRepository;

@Service
public class LigneCommandeAchatService {
	  private final LigneCommandeAchatRepository ligneCommandeAchatRepository;

	    public LigneCommandeAchatService(LigneCommandeAchatRepository ligneCommandeAchatRepository) {
	        this.ligneCommandeAchatRepository = ligneCommandeAchatRepository;
	    }

	    public List<LigneCommandeAchat> getAllLignesCommande() {
	        return ligneCommandeAchatRepository.findAll();
	    }

	    public Optional<LigneCommandeAchat> getLigneCommandeById(Long id) {
	        return ligneCommandeAchatRepository.findById(id);
	    }

	    public LigneCommandeAchat saveLigneCommande(LigneCommandeAchat ligneCommandeAchat) {
	        return ligneCommandeAchatRepository.save(ligneCommandeAchat);
	    }

	    public LigneCommandeAchat updateLigneCommande(Long id, LigneCommandeAchat ligneDetails) {
	        LigneCommandeAchat ligne = ligneCommandeAchatRepository.findById(id).orElse(null);

	        if (ligne != null) {
	            ligne.setCommande(ligneDetails.getCommande());
	            ligne.setProduit(ligneDetails.getProduit());
	            ligne.setQuantite(ligneDetails.getQuantite());
	            ligne.setPrixUnitaire(ligneDetails.getPrixUnitaire());

	            return ligneCommandeAchatRepository.save(ligne);
	        }

	        return null;
	    }

	    public void deleteLigneCommande(Long id) {
	        ligneCommandeAchatRepository.deleteById(id);
	    }

}
