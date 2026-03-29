import { Component, OnInit } from '@angular/core';
import { Historique } from 'src/app/models/historique';
import { HistoriqueService } from 'src/app/services/historique.service';

@Component({
  selector: 'app-historique',
  templateUrl: './historique.component.html',
  styleUrls: ['./historique.component.css']
})
export class HistoriqueComponent implements OnInit {

  historiques: Historique[] = [];

  nouveau: Historique = {
    fournisseur: { id: 1 },
    produit: '',
    quantite: 0,
    delaiLivraison: 0
  };

  modeModification = false;
  idEnCours: number | null = null;

  constructor(private service: HistoriqueService) {}

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void {
    this.service.getAll().subscribe(data => {
      this.historiques = data;
    });
  }

  ajouter(): void {
    this.service.create(this.nouveau).subscribe(() => {
      this.getAll();
      this.reset();
    });
  }

  supprimer(id: number): void {
    this.service.delete(id).subscribe(() => {
      this.getAll();
    });
  }

  charger(h: Historique): void {
    this.modeModification = true;
    this.idEnCours = h.id!;

    this.nouveau = {
      fournisseur: { id: h.fournisseur.id },
      produit: h.produit,
      quantite: h.quantite,
      delaiLivraison: h.delaiLivraison
    };
  }

  modifier(): void {
    if (this.idEnCours !== null) {
      this.service.update(this.idEnCours, this.nouveau).subscribe(() => {
        this.getAll();
        this.reset();
      });
    }
  }

  reset(): void {
    this.nouveau = {
      fournisseur: { id: 1 },
      produit: '',
      quantite: 0,
      delaiLivraison: 0
    };
    this.modeModification = false;
    this.idEnCours = null;
  }
}