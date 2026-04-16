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
  const payload = {
    fournisseurId: Number(this.nouveau.fournisseur.id),
    produit: this.nouveau.produit,
    quantite: Number(this.nouveau.quantite),
    delaiLivraison: Number(this.nouveau.delaiLivraison)
  };

  console.log('Payload historique :', JSON.stringify(payload, null, 2));

  this.service.create(payload as any).subscribe(() => {
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
    const payload = {
      fournisseurId: Number(this.nouveau.fournisseur.id),
      produit: this.nouveau.produit,
      quantite: Number(this.nouveau.quantite),
      delaiLivraison: Number(this.nouveau.delaiLivraison)
    };

    this.service.update(this.idEnCours, payload as any).subscribe(() => {
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