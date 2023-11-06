import { Component } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  title = 'MOIC-app';
  sessionStorageValue;

  constructor(private http: HttpClient) {
    sessionStorage.setItem("isFirstTime", "true");
  }

  ngOnInit(): void { }
}
