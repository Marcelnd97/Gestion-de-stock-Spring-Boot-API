import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CmdcltfrsService} from '../../services/cmdcltfrs.service';
import {LigneCommandeClientDto} from '../../../gs-api/src/models/ligne-commande-client-dto';

@Component({
  selector: 'app-mage-cmd-clt-frs',
  templateUrl: './page-cmd-clt-frs.component.html',
  styleUrls: ['./page-cmd-clt-frs.component.scss']
})
export class PageCmdCltFrsComponent implements OnInit {
    origin = '';
    listeCommandes: Array<any> = [];
    mapLignesCommande = new Map();
    mapPrixTotalCommande = new Map();

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private cmdCltFrsService: CmdcltfrsService) { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.origin = data.origin;
    });
    this.findAllCommandes();
  }

  findAllCommandes(): void {
    if (this.origin === 'client') {
        this.cmdCltFrsService.findAllCommandesClient()
            .subscribe(cmd => {
                this.listeCommandes = cmd;
                this.findAllLignesCommande();
            });
    } else if (this.origin === 'fournisseur') {
        this.cmdCltFrsService.findAllCommandesFournisseur()
            .subscribe(cmd => {
                this.listeCommandes = cmd;
                this.findAllLignesCommande();
            });
    }
  }

    findAllLignesCommande(): void {
        this.listeCommandes.forEach(cmd => {
            this.findLignesCommande(cmd.id);
        });
    }

    nouvelleCommande(): void {
        if (this.origin === 'client') {
            this.router.navigate(['nouvellecommandeclt']);
        } else if (this.origin === 'fournisseur') {
            this.router.navigate(['nouvellecommandefrs']);
        }
    }

    findLignesCommande(idCommande?: number): void {
        if (this.origin === 'client') {
            this.cmdCltFrsService.findAllLigneCommandesClient(idCommande)
                .subscribe(list => {
                    this.mapLignesCommande.set(idCommande, list);
                    this.mapPrixTotalCommande.set(idCommande, this.calculerTatalCmd(list));
                });
        } else if (this.origin === 'fournisseur') {
            this.cmdCltFrsService.findAllLigneCommandesFournisseur(idCommande)
                .subscribe(list => {
                    this.mapLignesCommande.set(idCommande, list);
                    this.mapPrixTotalCommande.set(idCommande, this.calculerTatalCmd(list));
                });
        }
    }

    calculerTatalCmd(list: Array<LigneCommandeClientDto>): number {
        let total = 0;
        list.forEach(ligne => {
            if (ligne.prixUnitaire && ligne.quantite) {
                total += +ligne.quantite * +ligne.prixUnitaire;
            }
        });
        return Math.floor(total);
    }

    calculerTotalCommande(id?: number): number {
        return this.mapPrixTotalCommande.get(id);
    }

  /*findAllCommandes(): void{
    if (this.origin === 'client'){
      this.cmdCltFrsService.findAllCommandeClient()
          .subscribe(cmdClt => {
            this.listeCommandes = cmdClt;
            this.findAllLignesCommande();
          });
    }else if (this.origin === 'fournisseur'){
      this.cmdCltFrsService.findAllCommandeFournisseur()
          .subscribe(cmdsfrs => {
            this.listeCommandes = cmdsfrs;
            this.findAllLignesCommande();
          });
    }
  }

  findAllLignesCommande(): void {
      this.listeCommandes.forEach(cmd => {
          this.findLignesCommandes(cmd.id);
      });
  }

  nouvelleCommande(): void{
    if (this.origin === 'client'){
        this.router.navigate(['nouvellecommandeclt']);
    }else if (this.origin === 'fournisseur'){
        this.router.navigate(['nouvellecommandefrs']);
    }
  }

  findLignesCommandes(idCommande?: number): void {
    if (this.origin === 'client') {
        this.cmdCltFrsService.findAllLigneCmdClt(idCommande)
        .subscribe(list => {
            this.mapLignesCommande.set(idCommande, list);
            this.mapPrixTotalCommande.set(idCommande, this.calculerTotalCmd(list));
        });
    } else if (this.origin === 'fournisseur') {
        this.cmdCltFrsService.findAllLigneCmdFrs(idCommande)
        .subscribe(list => {
            this.mapLignesCommande.set(idCommande, list);
            this.mapPrixTotalCommande.set(idCommande, this.calculerTotalCmd(list));
        });
    }
  }

  calculerTotalCmd(list: Array<LigneCommandeClientDto>): number {
    let total = 0;
    list.forEach(ligne => {
        if (ligne.prixUnitaire && ligne.quantite) {
            total += +ligne.quantite * +ligne.prixUnitaire;
        }
    });
    return Math.floor(total);
  }

  calculerTotalCommande(id?: number): number {
      return this.mapPrixTotalCommande.get(id);
  }*/
}
