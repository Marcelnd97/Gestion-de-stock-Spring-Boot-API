import { Injectable } from '@angular/core';
import {EntrepriseDto} from '../../gs-api/src/models/entreprise-dto';
import {Observable, of} from 'rxjs';
import {EntrepriseService} from '../../gs-api/src/services/entreprise.service';
import {UtilisateurDto} from '../../gs-api/src/models/utilisateur-dto';

@Injectable({
  providedIn: 'root'
})
export class EntreprisesService {

  constructor(private entrepriseService: EntrepriseService) { }

  sinscrire(entrepriseDto: EntrepriseDto): Observable<EntrepriseDto> {
    return this.entrepriseService.save(entrepriseDto);
  }

  findAllUsers(): Observable<UtilisateurDto[]> {
    return this.entrepriseService.findAll();
  }

  findAllEntreprise(): Observable<EntrepriseDto[]> {
    return this.entrepriseService.findAll();
  }
}
