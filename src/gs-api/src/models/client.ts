/* tslint:disable */
import { Adresse } from './adresse';
import { CommandeClient } from './commande-client';
export interface Client {
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
  commandeClients?: Array<CommandeClient>;
  username?: boolean;
}
