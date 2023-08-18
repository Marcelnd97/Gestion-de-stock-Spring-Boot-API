import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {EntreprisesService} from '../../../services/entreprises.service';
import {EntrepriseDto} from '../../../../gs-api/src/models/entreprise-dto';
import {MouveStockDto} from '../../../../gs-api/src/models/mouve-stock-dto';
import {MvstkService} from '../../../services/mvstk.service';

@Component({
  selector: 'app-page-profil',
  templateUrl: './page-profil.component.html',
  styleUrls: ['./page-profil.component.scss']
})
export class PageProfilComponent implements OnInit {
  profil: Array<EntrepriseDto> = [];

  constructor(private router: Router,
              private entreprisesService: EntreprisesService,
              private mvstkService: MvstkService) { }

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
