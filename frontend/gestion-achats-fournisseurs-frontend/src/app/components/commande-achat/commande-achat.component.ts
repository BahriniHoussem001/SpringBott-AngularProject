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

  constructor(
    private commandeAchatService: CommandeAchatService,
    private fournisseurService: FournisseurService
  ) { }

  ngOnInit(): void {
    this.getCommandes();
    this.getFournisseurs();
  }

  getCommandes(): void {
    this.commandeAchatService.getAllCommandes().subscribe({
      next: (data) => {
        this.commandes = data;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des commandes :', err);
      }
    });
  }

  getFournisseurs(): void {
    this.fournisseurService.getAllFournisseurs().subscribe({
      next: (data) => {
        this.fournisseurs = data;

        if (this.fournisseurs.length > 0 && !this.nouvelleCommande.fournisseur?.id) {
          this.nouvelleCommande.fournisseur = { id: this.fournisseurs[0].id! };
        }
      },
      error: (err) => {
        console.error('Erreur lors du chargement des fournisseurs :', err);
      }
    });
  }

  ajouterCommande(): void {
    if (
      !this.nouvelleCommande.fournisseur?.id ||
      !this.nouvelleCommande.date ||
      !this.nouvelleCommande.statut ||
      Number(this.nouvelleCommande.montant) <= 0
    ) {
      alert('Veuillez remplir correctement tous les champs.');
      return;
    }

    const commandeToSend = {
      fournisseur: {
        id: Number(this.nouvelleCommande.fournisseur.id)
      },
      date: this.nouvelleCommande.date,
      statut: this.nouvelleCommande.statut.trim(),
      montant: Number(this.nouvelleCommande.montant)
    };

    console.log('Payload envoyé pour ajout commande :', commandeToSend);

    this.commandeAchatService.createCommande(commandeToSend as CommandeAchat).subscribe({
      next: () => {
        this.getCommandes();
        this.reinitialiserFormulaire();
      },
      error: (err) => {
        console.error('Erreur lors de l’ajout de la commande :', err);
      }
    });
  }

  supprimerCommande(id: number): void {
    this.commandeAchatService.deleteCommande(id).subscribe({
      next: () => {
        this.getCommandes();
      },
      error: (err) => {
        console.error('Erreur lors de la suppression de la commande :', err);
      }
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
    if (this.idCommandeEnCours === null) {
      return;
    }

    if (
      !this.nouvelleCommande.fournisseur?.id ||
      !this.nouvelleCommande.date ||
      !this.nouvelleCommande.statut ||
      Number(this.nouvelleCommande.montant) <= 0
    ) {
      alert('Veuillez remplir correctement tous les champs.');
      return;
    }

    const commandeToSend = {
      fournisseur: {
        id: Number(this.nouvelleCommande.fournisseur.id)
      },
      date: this.nouvelleCommande.date,
      statut: this.nouvelleCommande.statut.trim(),
      montant: Number(this.nouvelleCommande.montant)
    };

    console.log('Payload envoyé pour modification commande :', commandeToSend);

    this.commandeAchatService.updateCommande(this.idCommandeEnCours, commandeToSend as CommandeAchat).subscribe({
      next: () => {
        this.getCommandes();
        this.reinitialiserFormulaire();
      },
      error: (err) => {
        console.error('Erreur lors de la modification de la commande :', err);
      }
    });
  }

  reinitialiserFormulaire(): void {
    this.nouvelleCommande = {
      fournisseur: { id: this.fournisseurs.length > 0 ? this.fournisseurs[0].id! : 1 },
      date: '',
      statut: '',
      montant: 0
    };

    this.modeModification = false;
    this.idCommandeEnCours = null;
  }
}