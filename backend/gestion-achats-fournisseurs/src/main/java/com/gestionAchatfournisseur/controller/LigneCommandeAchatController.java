package com.gestionAchatfournisseur.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.gestionAchatfournisseur.dto.LigneCommandeAchatRequest;
import com.gestionAchatfournisseur.entity.CommandeAchat;
import com.gestionAchatfournisseur.entity.LigneCommandeAchat;
import com.gestionAchatfournisseur.repo.CommandeAchatRepository;
import com.gestionAchatfournisseur.service.LigneCommandeAchatService;

@RestController
@RequestMapping("/api/lignes-commandes")
@CrossOrigin("*")
public class LigneCommandeAchatController {

    private final LigneCommandeAchatService ligneCommandeAchatService;
    private final CommandeAchatRepository commandeAchatRepository;

    public LigneCommandeAchatController(LigneCommandeAchatService ligneCommandeAchatService,
                                        CommandeAchatRepository commandeAchatRepository) {
        this.ligneCommandeAchatService = ligneCommandeAchatService;
        this.commandeAchatRepository = commandeAchatRepository;
    }

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