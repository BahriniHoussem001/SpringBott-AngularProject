import { Component, OnInit } from '@angular/core';
import { CommandeAchat } from 'src/app/models/commande-achat';
import { CommandeAchatService } from 'src/app/services/commande-achat.service';
import { FournisseurService } from 'src/app/services/fournisseur.service';
import { Fournisseur } from 'src/app/models/fournisseur';

@Component({
  selector: 'app-commande-achat',
  templateUrl: './commande-achat.component.html',
  styleUrls: ['./commande-achat.component.css']
})
export class CommandeAchatComponent implements OnInit {

  commandes: CommandeAchat[] = [];
  fournisseurs: Fournisseur[] = [];

  nouvelleCommande: CommandeAchat = {
    fournisseur: { id: 1 },
    date: '',
    statut: '',
    montant: 0
  };

  modeModification: boolean = false;
  idCommandeEnCours: number | null = null;

  constructor(private commandeAchatService: CommandeAchatService,private fournisseurService: FournisseurService) { }

  ngOnInit(): void {
    this.getCommandes();
    this.getFournisseurs();
  }

  getCommandes(): void {
    this.commandeAchatService.getAllCommandes().subscribe(data => {
      this.commandes = data;
    });
  }

  ajouterCommande(): void {
    this.commandeAchatService.createCommande(this.nouvelleCommande).subscribe(() => {
      this.getCommandes();
      this.reinitialiserFormulaire();
    });
  }

  supprimerCommande(id: number): void {
    this.commandeAchatService.deleteCommande(id).subscribe(() => {
      this.getCommandes();
    });
  }

  chargerCommandePourModification(commande: CommandeAchat): void {
    this.modeModification = true;
    this.idCommandeEnCours = commande.id!;

    this.nouvelleCommande = {
      fournisseur: { id: commande.fournisseur.id },
      date: commande.date,
      statut: commande.statut,
      montant: commande.montant
    };
  }

  modifierCommande(): void {
    if (this.idCommandeEnCours !== null) {
      this.commandeAchatService.updateCommande(this.idCommandeEnCours, this.nouvelleCommande).subscribe(() => {
        this.getCommandes();
        this.reinitialiserFormulaire();
      });
    }
  }

  reinitialiserFormulaire(): void {
    this.nouvelleCommande = {
      fournisseur: { id: 1 },
      date: '',
      statut: '',
      montant: 0
    };
    this.modeModification = false;
    this.idCommandeEnCours = null;
  }
  getFournisseurs(): void {
  this.fournisseurService.getAllFournisseurs().subscribe(data => {
    this.fournisseurs = data;
  });
}
}