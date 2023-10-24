import {Component, Input, OnInit} from '@angular/core';
import {ClientDto} from '../../../gs-api/src/models/client-dto';
import {ArticleDto} from '../../../gs-api/src/models/article-dto';
import {CommandeClientDto} from '../../../gs-api/src/models/commande-client-dto';
import {CommandeFournisseurDto} from '../../../gs-api/src/models/commande-fournisseur-dto';

@Component({
  selector: 'app-detail-cmd-clt-frs',
  templateUrl: './detail-cmd-clt-frs.component.html',
  styleUrls: ['./detail-cmd-clt-frs.component.scss']
})
export class DetailCmdCltFrsComponent implements OnInit {
  @Input()
  origin = '';
  @Input()
  commande: any = {};
  cltFrs: ClientDto | undefined = {};
  @Input()
  clientFournisseur: any = {};
  @Input()
  searchedArticle: ArticleDto = {};
  quantite: any;

  constructor() { }

  ngOnInit(): void {
    this.extractClientFournisseur();
  }

  extractClientFournisseur(): void{
    if (this.origin === 'client'){
      this.cltFrs = this.commande?.client;
    } else if (this.origin === 'fournisseur') {
      this.cltFrs = this.commande.fournisseur;
    }
  }

  modifierCommande(): void {
  }

  mapToClient(): CommandeClientDto{
    const cmdCltDto: CommandeClientDto = this.commande;
    cmdCltDto.client = this.clientFournisseur;
    return cmdCltDto;
  }

  mapToFournisseur(): CommandeFournisseurDto{
    const cmdFrsDto: CommandeFournisseurDto = this.commande;
    cmdFrsDto.fournisseur = this.clientFournisseur;
    return cmdFrsDto;
  }
}
