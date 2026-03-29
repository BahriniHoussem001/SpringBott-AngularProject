package com.gestionAchatfournisseur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestionAchatfournisseur.entity.HistoriqueAchats;
import com.gestionAchatfournisseur.repo.HistoriqueAchatsRepository;

@Service
public class HistoriqueAchatsService {
	  private final HistoriqueAchatsRepository historiqueAchatsRepository;

	    public HistoriqueAchatsService(HistoriqueAchatsRepository historiqueAchatsRepository) {
	        this.historiqueAchatsRepository = historiqueAchatsRepository;
	    }

	    public List<HistoriqueAchats> getAllHistoriques() {
	        return historiqueAchatsRepository.findAll();
	    }

	    public Optional<HistoriqueAchats> getHistoriqueById(Long id) {
	        return historiqueAchatsRepository.findById(id);
	    }

	    public HistoriqueAchats saveHistorique(HistoriqueAchats historiqueAchats) {
	        return historiqueAchatsRepository.save(historiqueAchats);
	    }

	    public HistoriqueAchats updateHistorique(Long id, HistoriqueAchats historiqueDetails) {
	        HistoriqueAchats historique = historiqueAchatsRepository.findById(id).orElse(null);

	        if (historique != null) {
	            historique.setFournisseur(historiqueDetails.getFournisseur());
	            historique.setProduit(historiqueDetails.getProduit());
	            historique.setQuantite(historiqueDetails.getQuantite());
	            historique.setDelaiLivraison(historiqueDetails.getDelaiLivraison());

	            return historiqueAchatsRepository.save(historique);
	        }

	        return null;
	    }

	    public void deleteHistorique(Long id) {
	        historiqueAchatsRepository.deleteById(id);
	    }
	    public List<HistoriqueAchats> comparerOffresParProduit(String produit) {
	        return historiqueAchatsRepository.findByProduit(produit);
	    }

}
