import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComparaisonComponent } from './comparaison.component';

describe('ComparaisonComponent', () => {
  let component: ComparaisonComponent;
  let fixture: ComponentFixture<ComparaisonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ComparaisonComponent]
    });
    fixture = TestBed.createComponent(ComparaisonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
