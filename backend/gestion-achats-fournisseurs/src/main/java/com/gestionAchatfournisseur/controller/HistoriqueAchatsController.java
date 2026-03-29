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

import com.gestionAchatfournisseur.entity.HistoriqueAchats;
import com.gestionAchatfournisseur.service.HistoriqueAchatsService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/historiques")
@CrossOrigin("*")

public class HistoriqueAchatsController {
@Autowired

private final HistoriqueAchatsService historiqueAchatsService;

public HistoriqueAchatsController(HistoriqueAchatsService historiqueAchatsService) {
    this.historiqueAchatsService = historiqueAchatsService;
}

@GetMapping
public List<HistoriqueAchats> getAllHistoriques() {
    return historiqueAchatsService.getAllHistoriques();
}

@GetMapping("/{id}")
public Optional<HistoriqueAchats> getHistoriqueById(@PathVariable("id") Long id) {
    return historiqueAchatsService.getHistoriqueById(id);
}

@PostMapping
public HistoriqueAchats createHistorique(@Valid @RequestBody HistoriqueAchats historiqueAchats) {
    return historiqueAchatsService.saveHistorique(historiqueAchats);
}

@PutMapping("/{id}")
public HistoriqueAchats updateHistorique(@PathVariable("id") Long id,
                                         @Valid @RequestBody HistoriqueAchats historiqueAchats) {
    return historiqueAchatsService.updateHistorique(id, historiqueAchats);
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
