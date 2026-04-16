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

  selectedCommandeId: number = 1;

  nouvelleLigne = {
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
    if (
      !this.selectedCommandeId ||
      !this.nouvelleLigne.produit ||
      Number(this.nouvelleLigne.quantite) <= 0 ||
      Number(this.nouvelleLigne.prixUnitaire) <= 0
    ) {
      alert('Veuillez remplir correctement tous les champs.');
      return;
    }

    const ligneToSend = {
      commandeId: Number(this.selectedCommandeId),
      produit: this.nouvelleLigne.produit.trim(),
      quantite: Number(this.nouvelleLigne.quantite),
      prixUnitaire: Number(this.nouvelleLigne.prixUnitaire)
    };

    console.log('Payload ligne commande :', JSON.stringify(ligneToSend, null, 2));

    this.service.create(ligneToSend as any).subscribe({
      next: () => {
        this.getAll();
        this.reset();
      },
      error: (err) => {
        console.error('Erreur ligne commande :', err);
      }
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

    this.selectedCommandeId = ligne.commande?.id ?? 1;

    this.nouvelleLigne = {
      produit: ligne.produit,
      quantite: ligne.quantite,
      prixUnitaire: ligne.prixUnitaire
    };
  }

  modifier(): void {
    if (this.idEnCours !== null) {
      const ligneToSend = {
        commandeId: Number(this.selectedCommandeId),
        produit: this.nouvelleLigne.produit.trim(),
        quantite: Number(this.nouvelleLigne.quantite),
        prixUnitaire: Number(this.nouvelleLigne.prixUnitaire)
      };

      console.log('Payload modification ligne commande :', JSON.stringify(ligneToSend, null, 2));

      this.service.update(this.idEnCours, ligneToSend as any).subscribe({
        next: () => {
          this.getAll();
          this.reset();
        },
        error: (err) => {
          console.error('Erreur modification ligne commande :', err);
        }
      });
    }
  }

  reset(): void {
    this.selectedCommandeId = 1;
    this.nouvelleLigne = {
      produit: '',
      quantite: 0,
      prixUnitaire: 0
    };
    this.modeModification = false;
    this.idEnCours = null;
  }
}