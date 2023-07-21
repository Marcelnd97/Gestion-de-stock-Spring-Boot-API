import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {EntreprisesService} from '../../../services/entreprises.service';
import {EntrepriseDto} from '../../../../gs-api/src/models/entreprise-dto';

@Component({
  selector: 'app-page-profil',
  templateUrl: './page-profil.component.html',
  styleUrls: ['./page-profil.component.scss']
})
export class PageProfilComponent implements OnInit {
  profil: Array<EntrepriseDto> = [];

  constructor(private router: Router,
              private entreprisesService: EntreprisesService) { }

  ngOnInit(): void {
    this.findAllUser();
  }

  findAllUser(): void{
    this.entreprisesService.findAllUsers()
        .subscribe(userProfile => {
          this.profil = userProfile;
        });
  }

  modifierMotDePasse(): void {
    this.router.navigate(['changermotdepasse']);
  }
}
