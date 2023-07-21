/* tslint:disable */
import { CategoryDto } from './category-dto';
export interface ArticleDto {
  id?: number;
  codeArticle?: string;
  designation?: string;
  prixUnitaireHT?: number;
  tauxTVA?: number;
  prixUnitaireTTC?: number;
  photo?: string;
  category?: CategoryDto;
  idEntreprise?: number;
}
