/* tslint:disable */
import { FournisseurDto } from './fournisseur-dto';
import { LigneCommandeFournisseurDto } from './ligne-commande-fournisseur-dto';
export interface CommandeFournisseurDto {
  id?: number;
  code?: string;
  etatCommande?: 'EN_PREPARATION' | 'VALIDEE' | 'LIVREE';
  dateCommande?: number;
  fournisseur?: FournisseurDto;
  idEntreprise?: number;
  lignecommandeFournisseur?: Array<LigneCommandeFournisseurDto>;
  commandeLivree?: boolean;
}
