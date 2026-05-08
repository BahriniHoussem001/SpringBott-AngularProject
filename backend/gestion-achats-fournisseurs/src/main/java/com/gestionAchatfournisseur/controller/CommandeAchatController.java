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

import com.gestionAchatfournisseur.dto.CommandeAchatRequest;
import com.gestionAchatfournisseur.entity.CommandeAchat;
import com.gestionAchatfournisseur.entity.Fournisseur;
import com.gestionAchatfournisseur.repo.FournisseurRepository;
import com.gestionAchatfournisseur.service.CommandeAchatService;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin("*")
public class CommandeAchatController {

    @Autowired
    private CommandeAchatService commandeAchatService;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @GetMapping
    public List<CommandeAchat> getAllCommandes() {
        return commandeAchatService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public Optional<CommandeAchat> getCommandeById(@PathVariable("id") Long id) {
        return commandeAchatService.getCommandeById(id);
    }

    @PostMapping
    public CommandeAchat createCommande(@RequestBody CommandeAchatRequest request) {
        Fournisseur fournisseur = fournisseurRepository.findById(request.getFournisseurId())
                .orElseThrow(() -> new RuntimeException("Fournisseur introuvable : " + request.getFournisseurId()));

        CommandeAchat commande = new CommandeAchat();
        commande.setFournisseur(fournisseur);
        commande.setDate(request.getDate());
        commande.setStatut(request.getStatut());
        commande.setMontant(request.getMontant());

        return commandeAchatService.saveCommande(commande);
    }

    @PutMapping("/{id}")
    public CommandeAchat updateCommande(@PathVariable("id") Long id, @RequestBody CommandeAchatRequest request) {
        Fournisseur fournisseur = fournisseurRepository.findById(request.getFournisseurId())
                .orElseThrow(() -> new RuntimeException("Fournisseur introuvable : " + request.getFournisseurId()));

        CommandeAchat commande = new CommandeAchat();
        commande.setId(id);
        commande.setFournisseur(fournisseur);
        commande.setDate(request.getDate());
        commande.setStatut(request.getStatut());
        commande.setMontant(request.getMontant());

        return commandeAchatService.updateCommande(id, commande);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable("id") Long id) {
        commandeAchatService.deleteCommande(id);
    }

    @GetMapping("/fournisseur/{fournisseurId}")
    public List<CommandeAchat> getCommandesByFournisseurId(@PathVariable("fournisseurId") Long fournisseurId) {
        return commandeAchatService.getCommandesByFournisseurId(fournisseurId);
    }
}