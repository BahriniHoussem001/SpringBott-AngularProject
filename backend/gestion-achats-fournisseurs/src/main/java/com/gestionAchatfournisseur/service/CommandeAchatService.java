package com.gestionAchatfournisseur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestionAchatfournisseur.entity.CommandeAchat;
import com.gestionAchatfournisseur.repo.CommandeAchatRepository;

@Service
public class CommandeAchatService {
	 private final CommandeAchatRepository commandeAchatRepository;

	    public CommandeAchatService(CommandeAchatRepository commandeAchatRepository) {
	        this.commandeAchatRepository = commandeAchatRepository;
	    }

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
	        CommandeAchat commande = commandeAchatRepository.findById(id).orElse(null);

	        if (commande != null) {
	            commande.setFournisseur(commandeDetails.getFournisseur());
	            commande.setDate(commandeDetails.getDate());
	            commande.setStatut(commandeDetails.getStatut());
	            commande.setMontant(commandeDetails.getMontant());

	            return commandeAchatRepository.save(commande);
	        }

	        return null;
	    }

	    public void deleteCommande(Long id) {
	        commandeAchatRepository.deleteById(id);
	    }
	    public List<CommandeAchat> getCommandesByFournisseurId(Long fournisseurId) {
	        return commandeAchatRepository.findByFournisseurId(fournisseurId);
	    }

}
