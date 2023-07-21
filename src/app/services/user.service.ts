import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {Observable, of} from 'rxjs';
import {AuthenticationService} from '../../gs-api/src/services/authentication.service';
import {AuthenticationRequest} from '../../gs-api/src/models/authentication-request';
import {AuthenticationResponse} from '../../gs-api/src/models/authentication-response';
import {UtilisateurDto} from '../../gs-api/src/models/utilisateur-dto';
import {UtilisateurService} from '../../gs-api/src/services/utilisateur.service';
import {ChangerMotDePasseUtilisateurDto} from '../../gs-api/src/models/changer-mot-de-passe-utilisateur-dto';
import {ArticleDto} from '../../gs-api/src/models/article-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private authenticationService: AuthenticationService,
              private router: Router,
              private utilisateurService: UtilisateurService) { }

  login(authenticationRequest: AuthenticationRequest): Observable<AuthenticationResponse>{
    return this.authenticationService.authenticate(authenticationRequest);
  }

  getUserByEmail(email?: string): Observable<UtilisateurDto>{
    if (email !== undefined){
      return this.utilisateurService.findByMail(email);
    }
    return of();
  }

  setAccessToken(authenticationResponse: AuthenticationResponse): void {
    localStorage.setItem('accessToken', JSON.stringify(authenticationResponse));
  }

  setConnectedUser(utilisateur: UtilisateurDto): void{
    localStorage.setItem('connectedUser', JSON.stringify(utilisateur));
  }

  getConnectedUser(): UtilisateurDto{
    if (localStorage.getItem('connectedUser')){
      return JSON.parse(localStorage.getItem('connectedUser') as string);
    }
    return {};
  }

  changerMotDePasse(changerMotDePasseDto: ChangerMotDePasseUtilisateurDto): Observable<ChangerMotDePasseUtilisateurDto> {
    return this.utilisateurService.changerMotDePasse(changerMotDePasseDto);
  }

  isUserLoggedAndAccessTokenValid(): boolean{
    if (localStorage.getItem('accessToken')){
      return true;
    }
    this.router.navigate(['login']);
    return false;
  }

}
