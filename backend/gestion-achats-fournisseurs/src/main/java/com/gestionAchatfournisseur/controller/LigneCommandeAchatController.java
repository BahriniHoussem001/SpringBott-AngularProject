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

import com.gestionAchatfournisseur.dto.LigneCommandeAchatRequest;
import com.gestionAchatfournisseur.entity.CommandeAchat;
import com.gestionAchatfournisseur.entity.LigneCommandeAchat;
import com.gestionAchatfournisseur.repo.CommandeAchatRepository;
import com.gestionAchatfournisseur.service.LigneCommandeAchatService;

@RestController
@RequestMapping("/api/lignes-commandes")
@CrossOrigin("*")
public class LigneCommandeAchatController {

    @Autowired
    private LigneCommandeAchatService ligneCommandeAchatService;

    @Autowired
    private CommandeAchatRepository commandeAchatRepository;

    @GetMapping
    public List<LigneCommandeAchat> getAllLignesCommande() {
        return ligneCommandeAchatService.getAllLignesCommande();
    }

    @GetMapping("/{id}")
    public Optional<LigneCommandeAchat> getLigneCommandeById(@PathVariable("id") Long id) {
        return ligneCommandeAchatService.getLigneCommandeById(id);
    }

    @PostMapping
    public LigneCommandeAchat createLigneCommande(@RequestBody LigneCommandeAchatRequest request) {
        CommandeAchat commande = commandeAchatRepository.findById(request.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'id : " + request.getCommandeId()));

        LigneCommandeAchat ligne = new LigneCommandeAchat();
        ligne.setCommande(commande);
        ligne.setProduit(request.getProduit());
        ligne.setQuantite(request.getQuantite());
        ligne.setPrixUnitaire(request.getPrixUnitaire());

        return ligneCommandeAchatService.saveLigneCommande(ligne);
    }

    @PutMapping("/{id}")
    public LigneCommandeAchat updateLigneCommande(@PathVariable("id") Long id,
                                                  @RequestBody LigneCommandeAchatRequest request) {
        CommandeAchat commande = commandeAchatRepository.findById(request.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec l'id : " + request.getCommandeId()));

        LigneCommandeAchat ligne = new LigneCommandeAchat();
        ligne.setId(id);
        ligne.setCommande(commande);
        ligne.setProduit(request.getProduit());
        ligne.setQuantite(request.getQuantite());
        ligne.setPrixUnitaire(request.getPrixUnitaire());

        return ligneCommandeAchatService.updateLigneCommande(id, ligne);
    }

    @DeleteMapping("/{id}")
    public void deleteLigneCommande(@PathVariable("id") Long id) {
        ligneCommandeAchatService.deleteLigneCommande(id);
    }
}