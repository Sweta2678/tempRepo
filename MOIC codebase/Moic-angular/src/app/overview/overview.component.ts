import { Component, OnInit } from '@angular/core';
import { RestService } from '../services/rest.service';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html'
})
export class OverviewComponent implements OnInit {

  constructor(private commonRes: RestService) {  
    this.checkedList = [];
  }

  sessionStrValue;
  hrefUrl = this.commonRes.REST_BASE_URL;
  getOverviewUpperTable = <any>[];
  getSeasonList;
  getChannelList;
  responseMessage: any;
  hideMessage: boolean;
  season = "";
  channel = "";
  seasonArc = "";
  channelArc = "";
  getSeasonArray;

  showDropDown;
  checkedList : any[];
  checked =false;
    

  ngOnInit(): void {
    //sessionStorage.setItem('token', 'true');
    /*to get data from rest service url----------------start */
    this.callOverviewDataApi();
    /* end */
  }

  callOverviewDataApi() {
     //console.log("sessionStorage-value:", sessionStorage.getItem('token'));
    this.commonRes.getOverviewData(this.sessionStrValue, this.season, this.channel, this.seasonArc, this.channelArc).subscribe(
      (response) => {
       // this.sessionStrValue = response.status;
       // sessionStorage.setItem('token', this.sessionStrValue);
        this.getOverviewUpperTable = response.result.overviewData.data;
        this.getSeasonList = response.result.seasons;
        this.getChannelList = response.result.channels;
        this.getSeasonArray = response.result.seasonsArray;
       // console.log("sessionStorage-value:", sessionStorage.getItem('token'));
        if (this.season == "" && this.channel == "") {
          this.season = this.getSeasonList[0];
          this.channel = this.getChannelList[0];
        }

        if (this.seasonArc == "" && this.channelArc == "") {
          this.seasonArc = this.getSeasonList[0];
          this.channelArc = this.getChannelList[0];
        }
        console.log("upperdata", this.getOverviewUpperTable);
      },
      (error) => {
        console.log("Error occured :" + error);
      }
    )
  }

  get valueWithKeys() {
    return Object.keys(this.getOverviewUpperTable);
  }

  getSelectedValue(status:Boolean,value:String){
    if(status){
      this.checkedList.push(value);  
    }else{
        var index = this.checkedList.indexOf(value);
        this.checkedList.splice(index,1);
    }
    
   // this.currentSelected = {checked : status,name:value};

}

resetSelected(val) {
  this.checkedList = [];
  for(let i =0;i < this.getSeasonArray.length;i++){
    this.getSeasonArray[i].checked = val;
  }
}

reloadCurrentPage() { 
  setTimeout(() => {
    window.location.reload();
   }, 500);
 }


}

