import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FournisseurComponent } from './components/fournisseur/fournisseur.component';
import { FormsModule } from '@angular/forms';
import { CommandeAchatComponent } from './components/commande-achat/commande-achat.component';
import { LigneCommandeComponent } from './components/ligne-commande/ligne-commande.component';
import { HistoriqueComponent } from './components/historique/historique.component';
import { EvaluationFournisseurComponent } from './components/evaluation-fournisseur/evaluation-fournisseur.component';
import { ComparaisonComponent } from './components/comparaison/comparaison.component';

@NgModule({
  declarations: [
    AppComponent,
    FournisseurComponent,
    CommandeAchatComponent,
    LigneCommandeComponent,
    HistoriqueComponent,
    EvaluationFournisseurComponent,
    ComparaisonComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }