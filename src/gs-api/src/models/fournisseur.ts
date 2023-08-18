/* tslint:disable */
import { Adresse } from './adresse';
import { CommandeFournisseur } from './commande-fournisseur';
export interface Fournisseur {
  id?: number;
  creationDate?: number;
  lastModifiedDate?: number;
  nom?: string;
  prenom?: string;
  idEntreprise?: number;
  adresse?: Adresse;
  photo?: string;
  mail?: string;
  numTel?: string;
  commandefournisseur?: Array<CommandeFournisseur>;
  username?: boolean;
}
