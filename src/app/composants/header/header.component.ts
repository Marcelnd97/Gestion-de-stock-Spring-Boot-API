import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {UtilisateurDto} from '../../../gs-api/src/models/utilisateur-dto';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  connectedUser: UtilisateurDto = {};
  codeArticles = '';
  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.connectedUser = this.userService.getConnectedUser();
  }

  logoutUser(): void {
    this.userService.logoutUser();
    window.location.reload();
    this.router.navigate(['login']);
  }

  profil(): void {
      this.router.navigate(['profil']);
  }
}
