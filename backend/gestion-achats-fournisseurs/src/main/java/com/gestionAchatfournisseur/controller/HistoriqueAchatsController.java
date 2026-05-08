package com.gestionAchatfournisseur.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionAchatfournisseur.dto.HistoriqueAchatsRequest;
import com.gestionAchatfournisseur.entity.Fournisseur;
import com.gestionAchatfournisseur.entity.HistoriqueAchats;
import com.gestionAchatfournisseur.service.HistoriqueAchatsService;

@RestController
@RequestMapping("/api/historiques")
@CrossOrigin("*")
public class HistoriqueAchatsController {

    @Autowired
    private HistoriqueAchatsService historiqueAchatsService;

    @GetMapping
    public List<HistoriqueAchats> getAllHistoriques() {
        return historiqueAchatsService.getAllHistoriques();
    }

    @GetMapping("/{id}")
    public Optional<HistoriqueAchats> getHistoriqueById(@PathVariable("id") Long id) {
        return historiqueAchatsService.getHistoriqueById(id);
    }

    @PostMapping
    public HistoriqueAchats createHistorique(@RequestBody HistoriqueAchatsRequest request) {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(request.getFournisseurId());

        HistoriqueAchats historique = new HistoriqueAchats();
        historique.setFournisseur(fournisseur);
        historique.setProduit(request.getProduit());
        historique.setQuantite(request.getQuantite());
        historique.setDelaiLivraison(request.getDelaiLivraison());

        return historiqueAchatsService.saveHistorique(historique);
    }

    @PutMapping("/{id}")
    public HistoriqueAchats updateHistorique(@PathVariable("id") Long id, @RequestBody HistoriqueAchatsRequest request) {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(request.getFournisseurId());

        HistoriqueAchats historique = new HistoriqueAchats();
        historique.setId(id);
        historique.setFournisseur(fournisseur);
        historique.setProduit(request.getProduit());
        historique.setQuantite(request.getQuantite());
        historique.setDelaiLivraison(request.getDelaiLivraison());

        return historiqueAchatsService.updateHistorique(id, historique);
    }

    @DeleteMapping("/{id}")
    public void deleteHistorique(@PathVariable("id") Long id) {
        historiqueAchatsService.deleteHistorique(id);
    }

    @GetMapping("/comparaison/{produit}")
    public List<HistoriqueAchats> comparerOffresParProduit(@PathVariable("produit") String produit) {
        return historiqueAchatsService.comparerOffresParProduit(produit);
    }
}