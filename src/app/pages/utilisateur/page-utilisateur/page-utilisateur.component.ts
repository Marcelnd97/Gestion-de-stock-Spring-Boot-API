import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {EntreprisesService} from '../../../services/entreprises.service';
import {EntrepriseDto} from '../../../../gs-api/src/models/entreprise-dto';

@Component({
  selector: 'app-page-utilisateur',
  templateUrl: './page-utilisateur.component.html',
  styleUrls: ['./page-utilisateur.component.scss']
})
export class PageUtilisateurComponent implements OnInit {
  listEntrepriseData: Array<EntrepriseDto> = [];

  utilisateur = '';
  page = 1;
  totalLength: any;

  constructor(private router: Router,
              private entrepriseService: EntreprisesService) { }

  ngOnInit(): void {
    this.findAllDataEntreprise();
  }

  findAllDataEntreprise(): void {
    this.entrepriseService.findAllEntreprise()
        .subscribe(entreprise => {
          this.listEntrepriseData = entreprise;
        });
  }

  nouvelUtilosateur(): void {
    this.router.navigate(['nouvelutilisateur']);
  }
}
