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

  selectedFournisseurId: number = 1;

  nouveau = {
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
    if (
      !this.selectedFournisseurId ||
      !this.nouveau.produit ||
      Number(this.nouveau.quantite) <= 0 ||
      Number(this.nouveau.delaiLivraison) < 0
    ) {
      alert('Veuillez remplir correctement tous les champs.');
      return;
    }

    const payload = {
      fournisseurId: Number(this.selectedFournisseurId),
      produit: this.nouveau.produit.trim(),
      quantite: Number(this.nouveau.quantite),
      delaiLivraison: Number(this.nouveau.delaiLivraison)
    };

    console.log('Payload historique :', JSON.stringify(payload, null, 2));

    this.service.create(payload as any).subscribe({
      next: () => {
        this.getAll();
        this.reset();
      },
      error: (err) => {
        console.error('Erreur historique :', err);
      }
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

    this.selectedFournisseurId = h.fournisseur?.id ?? 1;

    this.nouveau = {
      produit: h.produit,
      quantite: h.quantite,
      delaiLivraison: h.delaiLivraison
    };
  }

  modifier(): void {
    if (this.idEnCours !== null) {
      const payload = {
        fournisseurId: Number(this.selectedFournisseurId),
        produit: this.nouveau.produit.trim(),
        quantite: Number(this.nouveau.quantite),
        delaiLivraison: Number(this.nouveau.delaiLivraison)
      };

      console.log('Payload modification historique :', JSON.stringify(payload, null, 2));

      this.service.update(this.idEnCours, payload as any).subscribe({
        next: () => {
          this.getAll();
          this.reset();
        },
        error: (err) => {
          console.error('Erreur modification historique :', err);
        }
      });
    }
  }

  reset(): void {
    this.selectedFournisseurId = 1;
    this.nouveau = {
      produit: '',
      quantite: 0,
      delaiLivraison: 0
    };
    this.modeModification = false;
    this.idEnCours = null;
  }
}