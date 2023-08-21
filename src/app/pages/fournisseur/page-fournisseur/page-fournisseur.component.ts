import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {CltfrsService} from '../../../services/cltfrs.service';
import {FournisseurDto} from '../../../../gs-api/src/models/fournisseur-dto';

@Component({
  selector: 'app-page-fournisseur',
  templateUrl: './page-fournisseur.component.html',
  styleUrls: ['./page-fournisseur.component.scss']
})
export class PageFournisseurComponent implements OnInit {
  listFournisseur: Array<FournisseurDto> = [];
  errorMsg = '';

  fournisseur = '';
  totalLength: any;
  page = 1;

  constructor(private router: Router,
              private cltFrsService: CltfrsService) { }

  ngOnInit(): void {
    this.findAllFrs();
  }

  findAllFrs(): void {
    this.cltFrsService.findAllFournisseur()
        .subscribe(fournisseur => {
          this.listFournisseur = fournisseur;
        });
  }

  nouveauFournisseur(): void{
    this.router.navigate(['nouveaufournisseur']);
  }

  // cette methode permet le rafraichissement de la page après la suppression d'un client.

  handleSuppression(event: any): void {
    if (event === 'success') {
      this.findAllFrs();
    } else {
      this.errorMsg = event;
    }
  }
}
