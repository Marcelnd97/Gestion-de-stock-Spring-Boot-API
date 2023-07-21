/* tslint:disable */
import { ArticleDto } from './article-dto';
export interface MouveStockDto {
  id?: number;
  dateMouveStock?: number;
  quantite?: number;
  article?: ArticleDto;
  typeMouveStk?: 'ENTREE' | 'SORTIE' | 'CORRECTION_POS' | 'CORRECTION_NEG';
  sourceMvt?: 'COMMANDE_CLIENT' | 'COMMANDE_FOURNISSEUR' | 'VENTE';
  idEntreprise?: number;
}
