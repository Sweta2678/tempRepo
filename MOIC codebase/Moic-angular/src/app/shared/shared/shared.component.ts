import { Component, OnInit } from '@angular/core';
import { LoaderService } from "../../services/loader.service";
import { Subject } from "rxjs";
import { RestService } from '../../services/rest.service';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-shared',
  templateUrl: './shared.component.html'
})
export class SharedComponent implements OnInit {

  //isLoading: Subject<boolean> = this.loader.isLoading;
  isLoading: boolean = false;
  sessionStorageValue = sessionStorage.getItem('token');
  roleResPages;

  constructor(private loader: LoaderService, private commonRes: RestService, private router: Router) {

    this.loader.isLoading.subscribe((res) => {
      this.isLoading = res;
    });

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.sessionStorageValue = sessionStorage.getItem('token');
        var isFirstTime = sessionStorage.getItem("isFirstTime");
        if (this.sessionStorageValue && this.sessionStorageValue != null && isFirstTime == "true") {
          this.getNavigation();
        }
      }
    });
  }

  ngOnInit(): void {

  }

  getNavigation() {
    if (this.sessionStorageValue) {
      this.commonRes.getRoleAndLandingPage(this.sessionStorageValue).subscribe((response) => {
        if (response) {
          this.roleResPages = response.result;
          sessionStorage.setItem("isFirstTime", "false");
        }
      })
    }
  }

}
