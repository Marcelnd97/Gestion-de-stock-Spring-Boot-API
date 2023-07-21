import { Injectable } from '@angular/core';
import {CommandeClientDto} from '../../gs-api/src/models/commande-client-dto';
import {Observable} from 'rxjs';
import {CommandeClientsService} from '../../gs-api/src/services/commande-clients.service';
import {CommandefournisseurService} from '../../gs-api/src/services/commandefournisseur.service';
import {CommandeFournisseurDto} from '../../gs-api/src/models/commande-fournisseur-dto';
import {UserService} from './user.service';

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
}
