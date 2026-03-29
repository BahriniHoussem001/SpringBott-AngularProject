package com.gestionAchatfournisseur.controller;

import java.util.List;
import com.gestionAchatfournisseur.entity.EvaluationFournisseur;
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

import com.gestionAchatfournisseur.entity.Fournisseur;
import com.gestionAchatfournisseur.service.FournisseurService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fournisseurs")
@CrossOrigin("*")
public class FournisseurController {
	@Autowired
	 private final FournisseurService fournisseurService;

	    public FournisseurController(FournisseurService fournisseurService) {
	        this.fournisseurService = fournisseurService;
	    }

	    @GetMapping
	    public List<Fournisseur> getAllFournisseurs() {
	        return fournisseurService.getAllFournisseurs();
	    }

	    @GetMapping("/{id}")
	    public Optional<Fournisseur> getFournisseurById(@PathVariable("id") Long id) {
	        return fournisseurService.getFournisseurById(id);
	    }

	    @PostMapping
	    public Fournisseur createFournisseur(@Valid @RequestBody Fournisseur fournisseur) {
	        return fournisseurService.saveFournisseur(fournisseur);
	    }

	    @PutMapping("/{id}")
	    public Fournisseur updateFournisseur(@PathVariable("id") Long id, @Valid @RequestBody Fournisseur fournisseur) {
	        return fournisseurService.updateFournisseur(id, fournisseur);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteFournisseur(@PathVariable("id") Long id) {
	        fournisseurService.deleteFournisseur(id);
	    }
	    @GetMapping("/evaluation")
	    public List<EvaluationFournisseur> evaluerFournisseurs() {
	        return fournisseurService.evaluerFournisseurs();
	    }

}
