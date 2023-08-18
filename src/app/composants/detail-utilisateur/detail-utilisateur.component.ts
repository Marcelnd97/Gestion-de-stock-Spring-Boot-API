import {Component, Input, OnInit} from '@angular/core';
import {EntreprisesService} from '../../services/entreprises.service';
import {EntrepriseDto} from '../../../gs-api/src/models/entreprise-dto';
import {Router} from '@angular/router';

@Component({
  selector: 'app-detail-utilisateur',
  templateUrl: './detail-utilisateur.component.html',
  styleUrls: ['./detail-utilisateur.component.scss']
})
export class DetailUtilisateurComponent implements OnInit {
  @Input()
  entrepriseData: EntrepriseDto = {};

  constructor(private router: Router,
              private entrepriseService: EntreprisesService) { }

  ngOnInit(): void {
  }
  updatEntreprise(): void {
    this.entrepriseService.sinscrire(this.entrepriseData)
        .subscribe(entreprise => {
          this.entrepriseData = entreprise;
        });
  }
}
