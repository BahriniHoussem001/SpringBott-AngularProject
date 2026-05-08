package com.gestionAchatfournisseur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestionAchatfournisseur.entity.LigneCommandeAchat;
import com.gestionAchatfournisseur.repo.LigneCommandeAchatRepository;

@Service
public class LigneCommandeAchatService {

    @Autowired
    private LigneCommandeAchatRepository ligneCommandeAchatRepository;

    public List<LigneCommandeAchat> getAllLignesCommande() {
        return ligneCommandeAchatRepository.findAll();
    }

    public Optional<LigneCommandeAchat> getLigneCommandeById(Long id) {
        return ligneCommandeAchatRepository.findById(id);
    }

    public LigneCommandeAchat saveLigneCommande(LigneCommandeAchat ligneCommandeAchat) {
        return ligneCommandeAchatRepository.save(ligneCommandeAchat);
    }

    public LigneCommandeAchat updateLigneCommande(Long id, LigneCommandeAchat ligneDetails) {
        LigneCommandeAchat ligne = ligneCommandeAchatRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne commande non trouvée")
        );

        ligne.setCommande(ligneDetails.getCommande());
        ligne.setProduit(ligneDetails.getProduit());
        ligne.setQuantite(ligneDetails.getQuantite());
        ligne.setPrixUnitaire(ligneDetails.getPrixUnitaire());

        return ligneCommandeAchatRepository.save(ligne);
    }

    public void deleteLigneCommande(Long id) {
        if (!ligneCommandeAchatRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ligne commande non trouvée");
        }

        ligneCommandeAchatRepository.deleteById(id);
    }
}