export interface LigneCommande {
  id?: number;
  commande: {
    id: number;
  };
  produit: string;
  quantite: number;
  prixUnitaire: number;
}