import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FournisseurComponent } from './components/fournisseur/fournisseur.component';
import { CommandeAchatComponent } from './components/commande-achat/commande-achat.component';
import { LigneCommandeComponent } from './components/ligne-commande/ligne-commande.component';
import { HistoriqueComponent } from './components/historique/historique.component';
import { EvaluationFournisseurComponent } from './components/evaluation-fournisseur/evaluation-fournisseur.component';
import { ComparaisonComponent } from './components/comparaison/comparaison.component';
const routes: Routes = [
  { path: 'fournisseurs', component: FournisseurComponent },
  { path: '', redirectTo: 'fournisseurs', pathMatch: 'full' },
  { path: 'commandes', component: CommandeAchatComponent },
  { path: 'lignes-commandes', component: LigneCommandeComponent },
  { path: 'historiques', component: HistoriqueComponent },
  { path: 'evaluation-fournisseurs', component: EvaluationFournisseurComponent },
  { path: 'comparaison', component: ComparaisonComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }