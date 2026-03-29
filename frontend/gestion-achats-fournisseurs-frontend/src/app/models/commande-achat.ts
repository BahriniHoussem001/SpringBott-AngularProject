import { Fournisseur } from './fournisseur';

export interface CommandeAchat {
  id?: number;
  fournisseur: Partial<Fournisseur>;
  date: string;
  statut: string;
  montant: number;
}