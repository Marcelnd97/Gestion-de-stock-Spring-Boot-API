/* tslint:disable */
import { Client } from './client';
import { LigneCommandeClient } from './ligne-commande-client';
export interface CommandeClient {
  id?: number;
  creationDate?: number;
  lastModifiedDate?: number;
  code?: string;
  etatCommande?: 'EN_PREPARATION' | 'VALIDEE' | 'LIVREE';
  idEntreprise?: number;
  dateCommande?: number;
  client?: Client;
  lignecommandeClients?: Array<LigneCommandeClient>;
  username?: boolean;
}
