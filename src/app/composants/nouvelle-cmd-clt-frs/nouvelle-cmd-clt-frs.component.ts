import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CltfrsService} from '../../services/cltfrs.service';
import {ArticleService} from '../../services/article.service';
import {ArticleDto} from '../../../gs-api/src/models/article-dto';
import {LigneCommandeClientDto} from '../../../gs-api/src/models/ligne-commande-client-dto';
import {CommandeClientDto} from '../../../gs-api/src/models/commande-client-dto';
import {CmdcltfrsService} from '../../services/cmdcltfrs.service';
import {CommandeFournisseurDto} from '../../../gs-api/src/models/commande-fournisseur-dto';

@Component({
  selector: 'app-nouvelle-cmd-clt-frs',
  templateUrl: './nouvelle-cmd-clt-frs.component.html',
  styleUrls: ['./nouvelle-cmd-clt-frs.component.scss']
})
export class NouvelleCmdCltFrsComponent implements OnInit {
    selectedClientFournisseur: any = {};
    listClientFournisseur: Array<any> = [];
    listArticle: Array<ArticleDto> = [];
    searchedArticle: ArticleDto = {};

    origin = '';
    codeArticle = '';
    quantite = '';
    codeCommande = '' ;
    totalCommande = 0;
    articleNotYetSelected = false;
    errorMsg: Array<string> = [];

    ligneCommande: Array<any> = [];

    @Input()
    badgeClientFournisseur: any = {};

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private cltFrsService: CltfrsService,
              private articleService: ArticleService,
              private cmdCltFrsService: CmdcltfrsService) { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.origin = data.origin;
    });
    this.findAllClientFournisseurs();
    this.findAllArticle();
  }

  findAllClientFournisseurs(): void{
    if (this.origin === 'client') {
      this.cltFrsService.findAllClient()
          .subscribe(client => {
            this.listClientFournisseur = client;
          });
    } else if (this.origin === 'fournisseur') {
        this.cltFrsService.findAllFournisseur()
            .subscribe( fournisseur => {
                this.listClientFournisseur = fournisseur;
            });
    }
  }

  ajouterLigneCommande(): void {
   this.checkLigneCommande();
   this.calculerTotalCommande();

   this.searchedArticle = {};
   this.quantite = '';
   this.codeArticle = '';
   this.articleNotYetSelected = false;
   this.findAllArticle();
  }

  calculerTotalCommande(): void{
      this.totalCommande = 0;
      this.ligneCommande.forEach(ligne => {
          if (ligne.prixUnitaire && ligne.quantite) {
              this.totalCommande += +ligne.prixUnitaire * +ligne.quantite;
          }
      });
  }

  private checkLigneCommande(): void {
      /*ces instructions permettent l'addition de la quantite de commande pour un article
   au lieu de créer une nouvelle ligne*/
      const ligneCmdAlreadyExist = this.ligneCommande.find(lig =>
          lig.article?.codeArticle === this.searchedArticle.codeArticle);
      if (ligneCmdAlreadyExist) {
          this.ligneCommande.forEach(lig => {
              if (lig && lig.article?.codeArticle === this.searchedArticle.codeArticle) {
                  // @ts-ignore
                  lig.quantite = lig.quantite + +this.quantite;
              }
          });
/*____________________________________________________________________________________________________
          sinon on crée une nouvelle ligne de commande*/
      }else {
          const ligneCmd: LigneCommandeClientDto = {
              article: this.searchedArticle,
              prixUnitaire: this.searchedArticle.prixUnitaireTTC,
              quantite: +this.quantite
          };
          this.ligneCommande.push(ligneCmd);
      }
  }


  enregistrerCommande(): void {
    const commande = this.preparerCommande();
    if (this.origin === 'client') {
        this.cmdCltFrsService.enregistrerCommandeClient(commande as CommandeClientDto)
            .subscribe(cmd => {
                this.router.navigate(['commandesclient']);
            }, error => {
                this.errorMsg = error.error.errors;
            });
    } else if (this.origin === 'fournisseur') {
        this.cmdCltFrsService.enregistrerCommandeFournisseur(commande as CommandeFournisseurDto)
            .subscribe(cmd => {
                this.router.navigate(['commandesfournisseur']);
            }, error => {
                this.errorMsg = error.error.errors;
            });
    }
  }

  private preparerCommande(): any {
      if (this.origin === 'client') {
          return {
              client: this.selectedClientFournisseur,
              code: this.codeCommande,
              dateCommande: new Date().getTime(),
              etatCommande: 'EN_PREPARATION',
              ligneCommandeClients: this.ligneCommande
          };
      } else if (this.origin === 'fournisseur'){
          return {
              fournisseur: this.selectedClientFournisseur,
              code: this.codeCommande,
              dateCommande: new Date().getTime(),
              etatCommande: 'EN_PREPARATION',
              ligneCommandeFournisseurs: this.ligneCommande
          };
      }
  }

  cancel(): void{
    if (this.origin === 'client'){
      this.router.navigate(['commandesclient']);
    }else if (this.origin === 'fournisseur'){
      this.router.navigate(['commandesfournisseur']);
    }
  }

  findAllArticle(): void {
      this.articleService.findAllArticle()
          .subscribe(articles => {
              this.listArticle = articles;
          });
  }

  filterArticle(): void {
      if (this.codeArticle.length === 0) {
          this.findAllArticle();
      }
      this.listArticle = this.listArticle
          .filter(art => art.codeArticle?.includes(this.codeArticle) || art.designation?.includes(this.codeArticle));
  }

  selectArticleClick(article: ArticleDto): void{
  this.searchedArticle = article;
  this.codeArticle = article.codeArticle ? article.codeArticle : '';
  this.articleNotYetSelected = true;
    }
}
