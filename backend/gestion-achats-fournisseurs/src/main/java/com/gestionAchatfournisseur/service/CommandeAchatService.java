package com.gestionAchatfournisseur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestionAchatfournisseur.entity.CommandeAchat;
import com.gestionAchatfournisseur.repo.CommandeAchatRepository;

@Service
public class CommandeAchatService {

    @Autowired
    private CommandeAchatRepository commandeAchatRepository;

    public List<CommandeAchat> getAllCommandes() {
        return commandeAchatRepository.findAll();
    }

    public Optional<CommandeAchat> getCommandeById(Long id) {
        return commandeAchatRepository.findById(id);
    }

    public CommandeAchat saveCommande(CommandeAchat commandeAchat) {
        return commandeAchatRepository.save(commandeAchat);
    }

    public CommandeAchat updateCommande(Long id, CommandeAchat commandeDetails) {
        CommandeAchat commande = commandeAchatRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Commande non trouvée")
        );

        commande.setFournisseur(commandeDetails.getFournisseur());
        commande.setDate(commandeDetails.getDate());
        commande.setStatut(commandeDetails.getStatut());
        commande.setMontant(commandeDetails.getMontant());

        return commandeAchatRepository.save(commande);
    }

    public void deleteCommande(Long id) {
        if (!commandeAchatRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commande non trouvée");
        }

        commandeAchatRepository.deleteById(id);
    }

    public List<CommandeAchat> getCommandesByFournisseurId(Long fournisseurId) {
        return commandeAchatRepository.findByFournisseurId(fournisseurId);
    }
}