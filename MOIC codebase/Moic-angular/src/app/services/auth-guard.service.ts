import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()

export class AuthGuardService implements CanActivate {
  loginStatus;
  constructor(private router: Router) { }

  canActivate() {
    this.loginStatus = sessionStorage.getItem('token');
    if (this.loginStatus == null || this.loginStatus == "" || this.loginStatus == "false") {
      window.location.href = 'https://myapps.microsoft.com/';
      return false;
    }
    return true;
  }
}