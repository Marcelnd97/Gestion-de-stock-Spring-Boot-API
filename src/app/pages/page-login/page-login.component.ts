import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';
import {AuthenticationRequest} from '../../../gs-api/src/models/authentication-request';

@Component({
  selector: 'app-page-login',
  templateUrl: './page-login.component.html',
  styleUrls: ['./page-login.component.scss']
})
export class PageLoginComponent implements OnInit {
  authenticationRequest: AuthenticationRequest = {};
  errorMessage = '';

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  login() {
    this.userService.login(this.authenticationRequest).subscribe((data) => {
      this.userService.setAccessToken(data);
      this.getUserByEmail();
      this.router.navigate(['statistiques']);
    }, error => {
      this.errorMessage = 'Login et / ou mot de passe incorrecte';
    });
  }
  getUserByEmail(): void{
    this.userService.getUserByEmail(this.authenticationRequest.login).subscribe(user => {
      this.userService.setConnectedUser(user);
    });
  }

}
