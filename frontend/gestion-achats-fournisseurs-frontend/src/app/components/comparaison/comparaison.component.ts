import { Component } from '@angular/core';
import { HistoriqueService } from 'src/app/services/historique.service';

@Component({
  selector: 'app-comparaison',
  templateUrl: './comparaison.component.html',
  styleUrls: ['./comparaison.component.css']
})
export class ComparaisonComponent {

  produit: string = '';
  resultats: any[] = [];

  constructor(private historiqueService: HistoriqueService) {}

  comparer(): void {
    if (this.produit) {
      this.historiqueService.getComparaison(this.produit).subscribe(data => {
        this.resultats = data;
      });
    }
  }
}