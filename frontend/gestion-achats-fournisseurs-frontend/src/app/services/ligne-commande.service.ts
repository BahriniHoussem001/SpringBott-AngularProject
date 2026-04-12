import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LigneCommande } from '../models/ligne-commande';
import { environment } from 'src/environment';
@Injectable({
  providedIn: 'root'
})
export class LigneCommandeService {

  private apiUrl = `${environment.apiUrl}/lignes-commandes`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<LigneCommande[]> {
    return this.http.get<LigneCommande[]>(this.apiUrl);
  }

  create(ligne: LigneCommande): Observable<LigneCommande> {
    return this.http.post<LigneCommande>(this.apiUrl, ligne);
  }

  update(id: number, ligne: LigneCommande): Observable<LigneCommande> {
    return this.http.put<LigneCommande>(`${this.apiUrl}/${id}`, ligne);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}