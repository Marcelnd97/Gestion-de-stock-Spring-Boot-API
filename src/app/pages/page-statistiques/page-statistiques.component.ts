import { Component, OnInit } from '@angular/core';
import {Chart, registerables} from 'node_modules/chart.js';
import {UserService} from '../../services/user.service';
import {CategorieService} from '../../services/categorie.service';
import {CategoryDto} from '../../../gs-api/src/models/category-dto';
import {ArticleService} from '../../services/article.service';
import {ArticleDto} from '../../../gs-api/src/models/article-dto';
import {CltfrsService} from '../../services/cltfrs.service';
import {ClientDto} from '../../../gs-api/src/models/client-dto';
import {FournisseurDto} from '../../../gs-api/src/models/fournisseur-dto';
import {CmdcltfrsService} from '../../services/cmdcltfrs.service';
import {CommandeClientDto} from '../../../gs-api/src/models/commande-client-dto';
import {CommandeFournisseurDto} from '../../../gs-api/src/models/commande-fournisseur-dto';
import {EntrepriseDto} from '../../../gs-api/src/models/entreprise-dto';
import {EntreprisesService} from '../../services/entreprises.service';
Chart.register(...registerables);

@Component({
  selector: 'app-page-statistiques',
  templateUrl: './page-statistiques.component.html',
  styleUrls: ['./page-statistiques.component.scss']
})
export class PageStatistiquesComponent implements OnInit {
  chartData: any;
  labelData: any[] = [];
  realData: any[] = [];
  colorData: any[] = [];

  origin = '';
  categoryDataId: Array<CategoryDto> = [];
  articleDataId: Array<ArticleDto> = [];
  clientDataId: Array<ClientDto> = [];
  fournisseurDataId: Array<FournisseurDto> = [];
  cmdCltDataId: Array<CommandeClientDto> = [];
  cmdFrsDataId: Array<CommandeFournisseurDto> = [];
  entrepriseDataId: Array<EntrepriseDto> = [];

  constructor(private userService: UserService,
              private categorieService: CategorieService,
              private articleService: ArticleService,
              private cltFrsService: CltfrsService,
              private cmdCltFrsService: CmdcltfrsService,
              private cmdCltService: CmdcltfrsService,
              private entrepriseService: EntreprisesService) { }

  ngOnInit(): void {
    this.userService.GetChartInfo()
        .subscribe(result => {
      this.chartData = result;
      if (this.chartData != null){
        // tslint:disable-next-line:prefer-for-of
         for (let i = 0; i < this.chartData.length; i++) {
           // console.log(this.chartData[i]);
           this.labelData.push(this.chartData[i].name);
           this.realData.push(this.chartData[i].amount);
           this.colorData.push(this.chartData[i].colorCode);
         }
         this.renderChart(this.labelData, this.realData, this.colorData, 'bar', 'barchart');
         this.renderChart(this.labelData, this.realData, this.colorData, 'pie', 'piechart');
      }
    });
    this.findAllCateg();
    this.findAllArt();
    this.findAllClt();
    this.findAllFrs();
    this.findAllCmdClt();
    this.findAllCmdFrs();
    this.findAllEntreprise();
  }


    renderChart(labelData: any, mainData: any, colorData: any, type: any, id: any): void{
    const myChart = new Chart(id, {
      type,
      data: {
        labels: labelData,
        datasets: [{
          label: 'Eteindre',
          data: mainData,
          backgroundColor: colorData,
          borderColor: [
              'rgba(255, 99, 132, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

  findAllCateg(): void {
    this.categorieService.findAllCategory()
        .subscribe(idCategorie => {
          this.categoryDataId = idCategorie;
        });
  }

  findAllArt(): void {
    this.articleService.findAllArticle()
        .subscribe(idArticle => {
          this.articleDataId = idArticle;
        });
  }

  findAllClt(): void {
      this.cltFrsService.findAllClient()
          .subscribe(idClient => {
            this.clientDataId = idClient;
          });
  }

  findAllFrs(): void {
      this.cltFrsService.findAllFournisseur()
          .subscribe(idFournisseur => {
            this.fournisseurDataId = idFournisseur;
          });
  }

  findAllCmdClt(): void {
      this.cmdCltFrsService.findAllCommandesClient()
          .subscribe(idCmdClient => {
            this.cmdCltDataId = idCmdClient;
          });
  }

  findAllCmdFrs(): void {
      this.cmdCltFrsService.findAllCommandesFournisseur()
          .subscribe(idCmdFournisseur => {
            this.cmdFrsDataId = idCmdFournisseur;
          });
  }

  findAllEntreprise(): void {
      this.entrepriseService.findAllUsers()
          .subscribe(idEntreprise => {
              this.entrepriseDataId = idEntreprise;
          });
  }

}
