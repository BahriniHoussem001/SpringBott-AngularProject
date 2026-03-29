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

import com.gestionAchatfournisseur.entity.LigneCommandeAchat;
import com.gestionAchatfournisseur.service.LigneCommandeAchatService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lignes-commandes")
@CrossOrigin("*")
public class LigneCommandeAchatController {
@Autowired
private final LigneCommandeAchatService ligneCommandeAchatService;

public LigneCommandeAchatController(LigneCommandeAchatService ligneCommandeAchatService) {
    this.ligneCommandeAchatService = ligneCommandeAchatService;
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
public LigneCommandeAchat createLigneCommande(@Valid @RequestBody LigneCommandeAchat ligneCommandeAchat) {
    return ligneCommandeAchatService.saveLigneCommande(ligneCommandeAchat);
}

@PutMapping("/{id}")
public LigneCommandeAchat updateLigneCommande(@PathVariable("id") Long id,
                                              @Valid @RequestBody LigneCommandeAchat ligneCommandeAchat) {
    return ligneCommandeAchatService.updateLigneCommande(id, ligneCommandeAchat);
}

@DeleteMapping("/{id}")
public void deleteLigneCommande(@PathVariable("id") Long id) {
    ligneCommandeAchatService.deleteLigneCommande(id);
}
}
