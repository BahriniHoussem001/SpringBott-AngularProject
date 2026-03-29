import { Component, OnInit } from '@angular/core';
import { LigneCommande } from 'src/app/models/ligne-commande';
import { LigneCommandeService } from 'src/app/services/ligne-commande.service';

@Component({
  selector: 'app-ligne-commande',
  templateUrl: './ligne-commande.component.html',
  styleUrls: ['./ligne-commande.component.css']
})
export class LigneCommandeComponent implements OnInit {

  lignes: LigneCommande[] = [];

  nouvelleLigne: LigneCommande = {
    commande: { id: 1 },
    produit: '',
    quantite: 0,
    prixUnitaire: 0
  };

  modeModification = false;
  idEnCours: number | null = null;

  constructor(private service: LigneCommandeService) {}

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void {
    this.service.getAll().subscribe(data => {
      this.lignes = data;
    });
  }

  ajouter(): void {
    this.service.create(this.nouvelleLigne).subscribe(() => {
      this.getAll();
      this.reset();
    });
  }

  supprimer(id: number): void {
    this.service.delete(id).subscribe(() => {
      this.getAll();
    });
  }

  charger(ligne: LigneCommande): void {
    this.modeModification = true;
    this.idEnCours = ligne.id!;

    this.nouvelleLigne = {
      commande: { id: ligne.commande.id },
      produit: ligne.produit,
      quantite: ligne.quantite,
      prixUnitaire: ligne.prixUnitaire
    };
  }

  modifier(): void {
    if (this.idEnCours !== null) {
      this.service.update(this.idEnCours, this.nouvelleLigne).subscribe(() => {
        this.getAll();
        this.reset();
      });
    }
  }

  reset(): void {
    this.nouvelleLigne = {
      commande: { id: 1 },
      produit: '',
      quantite: 0,
      prixUnitaire: 0
    };
    this.modeModification = false;
    this.idEnCours = null;
  }
}