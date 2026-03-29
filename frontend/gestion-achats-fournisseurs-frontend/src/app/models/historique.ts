export interface Historique {
  id?: number;
  fournisseur: {
    id: number;
  };
  produit: string;
  quantite: number;
  delaiLivraison: number;
}