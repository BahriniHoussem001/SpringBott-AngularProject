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

import com.gestionAchatfournisseur.entity.CommandeAchat;
import com.gestionAchatfournisseur.service.CommandeAchatService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin("*")

public class CommandeAchatController {
	@Autowired
	private final CommandeAchatService commandeAchatService;

    public CommandeAchatController(CommandeAchatService commandeAchatService) {
        this.commandeAchatService = commandeAchatService;
    }

    @GetMapping
    public List<CommandeAchat> getAllCommandes() {
        return commandeAchatService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public Optional<CommandeAchat> getCommandeById(@PathVariable("id") Long id) {
        return commandeAchatService.getCommandeById(id);
    }

    @PostMapping
    public CommandeAchat createCommande(@Valid @RequestBody CommandeAchat commandeAchat) {
        return commandeAchatService.saveCommande(commandeAchat);
    }

    @PutMapping("/{id}")
    public CommandeAchat updateCommande(@PathVariable("id") Long id, @Valid @RequestBody CommandeAchat commandeAchat) {
        return commandeAchatService.updateCommande(id, commandeAchat);
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
