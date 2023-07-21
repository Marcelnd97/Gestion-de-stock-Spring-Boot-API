/* tslint:disable */
import { AdresseDto } from './adresse-dto';
import { EntrepriseDto } from './entreprise-dto';
import { RolesDto } from './roles-dto';
export interface UtilisateurDto {
  id?: number;
  nom?: string;
  prenom?: string;
  email?: string;
  dateDeNaissance?: number;
  motDePasse?: string;
  adresse?: AdresseDto;
  entreprise?: EntrepriseDto;
  photo?: string;
  roles?: Array<RolesDto>;
}
