import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RestService } from '../services/rest.service';

@Component({
  selector: 'app-landing',
  template: ``
})
export class LandingComponent implements OnInit {

  sessionStorageValue;
  roleLandingPage;


  constructor(private commonRes: RestService, private route: ActivatedRoute, private router: Router) {
//debugger;
    this.route.queryParamMap.subscribe(params => {
      var tokenValue = params.get('token');

        sessionStorage.setItem('token', tokenValue);
        this.sessionStorageValue = tokenValue;
        
      if (this.sessionStorageValue) {
        
        this.commonRes.getRoleAndLandingPage(this.sessionStorageValue).subscribe((response) => {
          if (response) {
            this.roleLandingPage = response.result.landingPage;
            sessionStorage.setItem('us4btn', response.result.hasS4UploadFile_action_uploadToS4);
            router.navigate(['/', this.roleLandingPage])
          }
        })
      } else {
        window.location.href = 'https://myapps.microsoft.com/';
      }
    });

  }

  ngOnInit(): void {


  }

}


