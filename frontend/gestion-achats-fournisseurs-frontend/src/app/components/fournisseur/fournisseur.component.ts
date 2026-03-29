import { Component, OnInit } from '@angular/core';
import { Fournisseur } from 'src/app/models/fournisseur';
import { FournisseurService } from 'src/app/services/fournisseur.service';

@Component({
  selector: 'app-fournisseur',
  templateUrl: './fournisseur.component.html',
  styleUrls: ['./fournisseur.component.css']
})
export class FournisseurComponent implements OnInit {

  fournisseurs: Fournisseur[] = [];

  nouveauFournisseur: Fournisseur = {
    nom: '',
    contact: '',
    qualiteService: '',
    note: 0
  };

  modeModification: boolean = false;
  idFournisseurEnCours: number | null = null;

  constructor(private fournisseurService: FournisseurService) { }

  ngOnInit(): void {
    this.getFournisseurs();
  }

  getFournisseurs(): void {
    this.fournisseurService.getAllFournisseurs().subscribe(data => {
      this.fournisseurs = data;
    });
  }

  ajouterFournisseur(): void {
    this.fournisseurService.createFournisseur(this.nouveauFournisseur).subscribe(() => {
      this.getFournisseurs();
      this.reinitialiserFormulaire();
    });
  }

  supprimerFournisseur(id: number): void {
    this.fournisseurService.deleteFournisseur(id).subscribe(() => {
      this.getFournisseurs();
    });
  }

  chargerFournisseurPourModification(fournisseur: Fournisseur): void {
    this.modeModification = true;
    this.idFournisseurEnCours = fournisseur.id!;

    this.nouveauFournisseur = {
      nom: fournisseur.nom,
      contact: fournisseur.contact,
      qualiteService: fournisseur.qualiteService,
      note: fournisseur.note
    };
  }

  modifierFournisseur(): void {
    if (this.idFournisseurEnCours !== null) {
      this.fournisseurService.updateFournisseur(this.idFournisseurEnCours, this.nouveauFournisseur).subscribe(() => {
        this.getFournisseurs();
        this.reinitialiserFormulaire();
      });
    }
  }

  reinitialiserFormulaire(): void {
    this.nouveauFournisseur = {
      nom: '',
      contact: '',
      qualiteService: '',
      note: 0
    };
    this.modeModification = false;
    this.idFournisseurEnCours = null;
  }
}