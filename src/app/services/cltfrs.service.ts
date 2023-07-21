import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {ClientDto} from '../../gs-api/src/models/client-dto';
import {ClientService} from '../../gs-api/src/services/client.service';
import {FournisseurService} from '../../gs-api/src/services/fournisseur.service';
import {UserService} from './user.service';
import {FournisseurDto} from '../../gs-api/src/models/fournisseur-dto';

@Injectable({
  providedIn: 'root'
})
export class CltfrsService {

  constructor(private client: ClientService,
              private fournisseur: FournisseurService,
              private userService: UserService) { }

  enregistrerClt(clientDto: ClientDto): Observable<ClientDto> {
    clientDto.idEntreprise = this.userService.getConnectedUser().entreprise?.id;
    return this.client.save(clientDto);
  }
  enregistrerFrs(fournisseurDto: FournisseurDto): Observable<FournisseurDto> {
    fournisseurDto.idEntreprise = this.userService.getConnectedUser().entreprise?.id;
    return this.fournisseur.save(fournisseurDto);
  }

  findAllClient(): Observable<ClientDto[]> {
    return this.client.findAll();
  }

  findAllFournisseur(): Observable<FournisseurDto[]> {
    return this.fournisseur.findAll();
  }

  findCltById(idClient: number): Observable<ClientDto>{
    if (idClient) {
      return this.client.findById(idClient);
    }else {
      return of();
    }
  }

  findFrsById(idFournisseur: number): Observable<FournisseurDto>{
    if (idFournisseur) {
      return this.fournisseur.findById(idFournisseur);
    }else {
      return of();
    }
  }

  deleteCltById(idClient: number): Observable<any> {
    if (idClient){
      return this.client.delete(idClient);
    }else {
      return of();
    }
  }
  deleteFrsById(idFournisseur: number): Observable<any> {
    if (idFournisseur){
      return this.fournisseur.delete(idFournisseur);
    }else {
      return of();
    }
  }
}
