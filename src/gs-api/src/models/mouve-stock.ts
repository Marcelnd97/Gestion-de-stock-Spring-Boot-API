/* tslint:disable */
import { Article } from './article';
export interface MouveStock {
  id?: number;
  creationDate?: number;
  lastModifiedDate?: number;
  dateMouveStock?: number;
  quantite?: number;
  article?: Article;
  typeMouveStk?: 'ENTREE' | 'SORTIE' | 'CORRECTION_POS' | 'CORRECTION_NEG';
  sourceMvt?: 'COMMANDE_CLIENT' | 'COMMANDE_FOURNISSEUR' | 'VENTE';
  idEntreprise?: number;
  username?: boolean;
}
