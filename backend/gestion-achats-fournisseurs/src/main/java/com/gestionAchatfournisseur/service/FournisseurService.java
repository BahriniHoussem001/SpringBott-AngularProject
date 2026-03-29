package com.gestionAchatfournisseur.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestionAchatfournisseur.entity.EvaluationFournisseur;
import com.gestionAchatfournisseur.entity.Fournisseur;
import com.gestionAchatfournisseur.entity.HistoriqueAchats;
import com.gestionAchatfournisseur.repo.FournisseurRepository;

@Service
public class FournisseurService {
	 private final FournisseurRepository fournisseurRepository;

	    public FournisseurService(FournisseurRepository fournisseurRepository) {
	        this.fournisseurRepository = fournisseurRepository;
	    }

	    public List<Fournisseur> getAllFournisseurs() {
	        return fournisseurRepository.findAll();
	    }

	    public Optional<Fournisseur> getFournisseurById(Long id) {
	        return fournisseurRepository.findById(id);
	    }

	    public Fournisseur saveFournisseur(Fournisseur fournisseur) {
	        return fournisseurRepository.save(fournisseur);
	    }

	    public Fournisseur updateFournisseur(Long id, Fournisseur fournisseurDetails) {
	        Fournisseur fournisseur = fournisseurRepository.findById(id).orElse(null);

	        if (fournisseur != null) {
	            fournisseur.setNom(fournisseurDetails.getNom());
	            fournisseur.setContact(fournisseurDetails.getContact());
	            fournisseur.setQualiteService(fournisseurDetails.getQualiteService());
	            fournisseur.setNote(fournisseurDetails.getNote());

	            return fournisseurRepository.save(fournisseur);
	        }

	        return null;
	    }

	    public void deleteFournisseur(Long id) {
	        fournisseurRepository.deleteById(id);
	    }
	    public List<EvaluationFournisseur> evaluerFournisseurs() {
	        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
	        List<EvaluationFournisseur> evaluations = new ArrayList<>();

	        for (Fournisseur fournisseur : fournisseurs) {
	            List<HistoriqueAchats> historiques = fournisseur.getHistoriques();

	            double moyenneDelai = 0.0;

	            if (historiques != null && !historiques.isEmpty()) {
	                int somme = 0;
	                for (HistoriqueAchats historique : historiques) {
	                    somme += historique.getDelaiLivraison();
	                }
	                moyenneDelai = (double) somme / historiques.size();
	            }

	            String efficacite = "Faible";

	            if (fournisseur.getNote() >= 8 && moyenneDelai > 0 && moyenneDelai <= 7) {
	                efficacite = "Efficace";
	            } else if (fournisseur.getNote() >= 5 && moyenneDelai > 0 && moyenneDelai <= 15) {
	                efficacite = "Moyen";
	            }

	            EvaluationFournisseur evaluation = new EvaluationFournisseur(
	                    fournisseur.getId(),
	                    fournisseur.getNom(),
	                    fournisseur.getNote(),
	                    fournisseur.getQualiteService(),
	                    moyenneDelai,
	                    efficacite
	            );

	            evaluations.add(evaluation);
	        }

	        return evaluations;
	    }

}
