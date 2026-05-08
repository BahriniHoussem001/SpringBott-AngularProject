package com.gestionAchatfournisseur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestionAchatfournisseur.entity.HistoriqueAchats;
import com.gestionAchatfournisseur.repo.HistoriqueAchatsRepository;

@Service
public class HistoriqueAchatsService {

    @Autowired
    private HistoriqueAchatsRepository historiqueAchatsRepository;

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
        HistoriqueAchats historique = historiqueAchatsRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Historique non trouvé")
        );

        historique.setFournisseur(historiqueDetails.getFournisseur());
        historique.setProduit(historiqueDetails.getProduit());
        historique.setQuantite(historiqueDetails.getQuantite());
        historique.setDelaiLivraison(historiqueDetails.getDelaiLivraison());

        return historiqueAchatsRepository.save(historique);
    }

    public void deleteHistorique(Long id) {
        if (!historiqueAchatsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Historique non trouvé");
        }

        historiqueAchatsRepository.deleteById(id);
    }

    public List<HistoriqueAchats> comparerOffresParProduit(String produit) {
        return historiqueAchatsRepository.findByProduit(produit);
    }
}