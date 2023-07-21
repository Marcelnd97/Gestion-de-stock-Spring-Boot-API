import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ChangerMotDePasseUtilisateurDto} from '../../../../gs-api/src/models/changer-mot-de-passe-utilisateur-dto';
import {UserService} from '../../../services/user.service';

@Component({
  selector: 'app-changer-mot-de-passe',
  templateUrl: './changer-mot-de-passe.component.html',
  styleUrls: ['./changer-mot-de-passe.component.scss']
})
export class ChangerMotDePasseComponent implements OnInit {
  changerMotDePasseDto: ChangerMotDePasseUtilisateurDto = {};
  ancientMotDePasse = '';
  constructor(private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    if (localStorage.getItem('origin') && localStorage.getItem('origin') === 'inscription'){
      this.ancientMotDePasse = 'som3R@nd0mP@$$word';
      localStorage.removeItem('origin');
    }
  }

  changerMotDePasseUtilisateur(): void{
    this.changerMotDePasseDto.id = this.userService.getConnectedUser().id;
    this.userService.changerMotDePasse(this.changerMotDePasseDto)
        .subscribe(data => {
          this.router.navigate(['profil']);
        });
  }

  cancel(): void{
    this.router.navigate(['profil']);
  }
}
