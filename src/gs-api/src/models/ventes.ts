/* tslint:disable */
import { LigneVente } from './ligne-vente';
export interface Ventes {
  id?: number;
  creationDate?: number;
  lastModifiedDate?: number;
  code?: string;
  dateVente?: number;
  commantaire?: string;
  idEntreprise?: number;
  ligneVentes?: Array<LigneVente>;
  username?: boolean;
}
