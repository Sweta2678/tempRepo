import { Component, OnInit } from '@angular/core';
import { RestService } from '../services/rest.service';

@Component({
  selector: 'app-s4-upload',
  templateUrl: './s4-upload.component.html'
})
export class S4UploadComponent implements OnInit {

  hrefUrl = this.commonRes.REST_BASE_URL;
  sessionStorageValue = sessionStorage.getItem('token');
  showUploadBtn = JSON.parse(sessionStorage.getItem('us4btn'));
  confirmMessage: boolean;
  notificationMsg: boolean;
  responseMessage: any;
  getSeasonList;
  getChannelList;
  season = "";
  channel = "";

  getSeasonArray;
  showDropDown;
  checkedList : any[];
  checked =false;
  
  constructor(private commonRes: RestService) { this.checkedList = []; }

  ngOnInit(): void {
    
    /*to get data from rest service url----------------start */

    this.commonRes.getUploadToS4Data().subscribe(
      (response) => {
        this.getChannelList = response.result.channels;
       // this.getSeasonList = response.result.seasons;
        this.getSeasonArray = response.result.seasonsArray;

        if (this.season == "" && this.channel == "") {
          this.season = this.getSeasonArray[0].name;
          this.channel = this.getChannelList[0];
        }
      },
      (error) => {
        console.log("Error occured :" + error);
      }
    )
    /* end */

  }

  uploadS4ConfirmBtn() {
    this.commonRes.uploadToS4(this.season, this.channel, this.sessionStorageValue).subscribe((response) => {
      if (response) {
        this.responseMessage = response['message'];
        this.confirmMessage = false;
        this.notificationMsg = true;
        this.resetSelected(false);
        console.log('Upload response is...', response)
      }
    }, error => {
      if (error) {
        this.responseMessage = error['message'];
        this.confirmMessage = false;
        this.notificationMsg = true;
        console.log('Upload error is..', error)
      }
    })
  }

  getSelectedValue(status:Boolean,value:String){

    if(status){
      this.checkedList.push(value);  
     this.season = this.checkedList.join(', ');
    }else{
        var index = this.checkedList.indexOf(value);
        this.checkedList.splice(index,1);
    }
    
    //this.currentSelected = {checked : status,name:value};

}

resetSelected(val) {
  this.checkedList = [];
  for(let i =0;i < this.getSeasonArray.length;i++){
    this.getSeasonArray[i].checked = val;
  }
}

}
