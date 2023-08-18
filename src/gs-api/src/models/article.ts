/* tslint:disable */
import { Category } from './category';
import { LigneVente } from './ligne-vente';
import { LigneCommandeClient } from './ligne-commande-client';
import { LigneCommandeFournisseur } from './ligne-commande-fournisseur';
import { MouveStock } from './mouve-stock';
export interface Article {
  id?: number;
  creationDate?: number;
  lastModifiedDate?: number;
  codeArticle?: string;
  idEntreprise?: number;
  totalStock?: number;
  designation?: string;
  prixUnitaireHT?: number;
  tauxTVA?: number;
  prixUnitaireTTC?: number;
  photo?: string;
  category?: Category;
  ligneVentes?: Array<LigneVente>;
  ligneCommandeClients?: Array<LigneCommandeClient>;
  ligneCommandeFournisseurs?: Array<LigneCommandeFournisseur>;
  mouveStocks?: Array<MouveStock>;
  username?: boolean;
}
