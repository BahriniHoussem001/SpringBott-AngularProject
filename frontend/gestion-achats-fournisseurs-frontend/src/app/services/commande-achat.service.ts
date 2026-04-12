import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommandeAchat } from '../models/commande-achat';

@Injectable({
  providedIn: 'root'
})
export class CommandeAchatService {

  private apiUrl = 'http://136.112.59.167:9096/api/commandes';

  constructor(private http: HttpClient) { }

  getAllCommandes(): Observable<CommandeAchat[]> {
    return this.http.get<CommandeAchat[]>(this.apiUrl);
  }

  createCommande(commande: CommandeAchat): Observable<CommandeAchat> {
    return this.http.post<CommandeAchat>(this.apiUrl, commande);
  }

  updateCommande(id: number, commande: CommandeAchat): Observable<CommandeAchat> {
    return this.http.put<CommandeAchat>(`${this.apiUrl}/${id}`, commande);
  }

  deleteCommande(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}