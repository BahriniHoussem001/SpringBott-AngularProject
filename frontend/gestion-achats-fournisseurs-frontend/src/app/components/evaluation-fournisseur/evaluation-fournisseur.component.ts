import { Component, OnInit } from '@angular/core';
import { EvaluationFournisseur } from 'src/app/models/evaluation-fournisseur';
import { FournisseurService } from 'src/app/services/fournisseur.service';

@Component({
  selector: 'app-evaluation-fournisseur',
  templateUrl: './evaluation-fournisseur.component.html',
  styleUrls: ['./evaluation-fournisseur.component.css']
})
export class EvaluationFournisseurComponent implements OnInit {

  evaluations: EvaluationFournisseur[] = [];

  constructor(private fournisseurService: FournisseurService) {}

  ngOnInit(): void {
    this.getEvaluations();
  }

  getEvaluations(): void {
    this.fournisseurService.getEvaluations().subscribe(data => {
      this.evaluations = data;
    });
  }
}