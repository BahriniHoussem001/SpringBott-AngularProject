import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Historique } from '../models/historique';

@Injectable({
  providedIn: 'root'
})
export class HistoriqueService {

  private apiUrl = 'http://136.112.59.167:9096/api/historiques';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Historique[]> {
    return this.http.get<Historique[]>(this.apiUrl);
  }

  create(h: Historique): Observable<Historique> {
    return this.http.post<Historique>(this.apiUrl, h);
  }

  update(id: number, h: Historique): Observable<Historique> {
    return this.http.put<Historique>(`${this.apiUrl}/${id}`, h);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  getComparaison(produit: string): Observable<any[]> {
  return this.http.get<any[]>(`${this.apiUrl}/comparaison/${produit}`);
}
}