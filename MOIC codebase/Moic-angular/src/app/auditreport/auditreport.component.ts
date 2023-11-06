import { Component, OnInit } from '@angular/core';
import { RestService } from '../services/rest.service';

@Component({
  selector: 'app-auditreport',
  templateUrl: './auditreport.component.html',
  styleUrls: []
})
export class AuditreportComponent implements OnInit {

  constructor(private commonRes: RestService) { }

  hrefUrl = this.commonRes.REST_BASE_URL;
  fromDateValue: Date;
  toDateValue: Date;
  user = null;
  getUserList: string[];

  ngOnInit(): void {
    this.commonRes.getUserId().subscribe(
      (response) => {
          this.getUserList = response.result.data;
      },
      (error) => {
          console.log("Error occured :" + error);
      }
  )

  }

}
