import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvaluationFournisseurComponent } from './evaluation-fournisseur.component';

describe('EvaluationFournisseurComponent', () => {
  let component: EvaluationFournisseurComponent;
  let fixture: ComponentFixture<EvaluationFournisseurComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EvaluationFournisseurComponent]
    });
    fixture = TestBed.createComponent(EvaluationFournisseurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
