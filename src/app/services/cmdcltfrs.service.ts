import { Injectable } from '@angular/core';
import {CommandeClientDto} from '../../gs-api/src/models/commande-client-dto';
import {Observable, of} from 'rxjs';
import {CommandeClientsService} from '../../gs-api/src/services/commande-clients.service';
import {CommandefournisseurService} from '../../gs-api/src/services/commandefournisseur.service';
import {CommandeFournisseurDto} from '../../gs-api/src/models/commande-fournisseur-dto';
import {UserService} from './user.service';
import {LigneCommandeClientDto} from '../../gs-api/src/models/ligne-commande-client-dto';
import {LigneCommandeFournisseurDto} from '../../gs-api/src/models/ligne-commande-fournisseur-dto';

@Injectable({
  providedIn: 'root'
})
export class CmdcltfrsService {

  constructor(private commandeClientService: CommandeClientsService,
              private  commandeFournisseurService: CommandefournisseurService,
              private userService: UserService) { }

  enregistrerCommandeClient(commandeClientDto: CommandeClientDto): Observable<CommandeClientDto> {
    commandeClientDto.idEntreprise = this.userService.getConnectedUser().entreprise?.id;
    return this.commandeClientService.save(commandeClientDto);
  }

  enregistrerCommandeFournisseur(commandeFournisseurDto: CommandeFournisseurDto): Observable<CommandeFournisseurDto> {
    commandeFournisseurDto.idEntreprise = this.userService.getConnectedUser().entreprise?.id;
    return this.commandeFournisseurService.save(commandeFournisseurDto);
  }

  findAllCommandesClient(): Observable<CommandeClientDto[]> {
    return this.commandeClientService.findAll();
  }

  findAllCommandesFournisseur(): Observable<CommandeFournisseurDto[]> {
    return this.commandeFournisseurService.findAll();
  }

  findAllLigneCommandesClient(idCmd?: number): Observable<LigneCommandeClientDto[]> {
    if (idCmd) {
      return this.commandeClientService.findAllLignesCommandeClientByIdCommandeClient(idCmd);
    }
    return of();
  }

  findAllLigneCommandesFournisseur(idCmd?: number): Observable<LigneCommandeFournisseurDto[]> {
    if (idCmd) {
      return this.commandeFournisseurService.findAllLignesCommandesFournisseurByCommandeFournisseurId(idCmd);
    }
    return of();
  }
}
