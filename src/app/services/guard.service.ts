import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {UserService} from './user.service';

@Injectable({
  providedIn: 'root'
})
export class GuardService implements CanActivate{

  constructor(private router: Router, private userService: UserService) { }
  // tslint:disable-next-line:max-line-length
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.userService.isUserLoggedAndAccessTokenValid();
  }
}
