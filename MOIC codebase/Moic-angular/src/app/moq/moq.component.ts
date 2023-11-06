import { Component, OnInit, ViewChild } from '@angular/core';
import { RestService } from '../services/rest.service';

@Component({
  selector: 'app-moq',
  templateUrl: './moq.component.html'
})

export class MoqComponent implements OnInit {

  constructor(private commonRes: RestService) { this.checkedList = []; }

  hrefUrl = this.commonRes.REST_BASE_URL;
  sessionStorageValue = sessionStorage.getItem('token');
  getMoqTableData = <any>[];
  getMoqUpperTable = <any>[];
  displayedColumnsHeading: string[];
  getSkuList: string[];
  deleteList: string[];
  getSeasonList;
  getChannelList;
  clonedProducts = {};
  sku: any;
  responseMessage: any;
  hideMessage: boolean;
  season = "";
  channel = "";

  getSeasonArray;
  showDropDown;
  checkedList : any[];
  checked =false;

  @ViewChild('dt') dt: any;

  ngOnInit(): void {
    /*to get data from rest service url----------------start */
    this.callMoqDataApi();
    /* end */
  }

  callMoqDataApi() {
    this.commonRes.getMoqData(this.season, this.channel).subscribe(
      (response) => {
        this.getMoqUpperTable = response.result.overviewData.data;
        this.getMoqTableData = response.result.moqData.data;
        this.getSeasonList = response.result.seasons;
        this.getChannelList = response.result.channels;
        this.displayedColumnsHeading = response.result.moqData.columns;
        this.deleteList = ["true", "false"];
        this.getSeasonArray = response.result.seasonsArray;

        if (this.season == "" && this.channel == "") {
          this.season = this.getSeasonList[0];
          this.channel = this.getChannelList[0];
        }

        console.log("upperdata", this.getMoqUpperTable);
      },
      (error) => {
        console.log("Error occured :" + error);
      }
    )
  }

  onRowEditInit(rowdata) {
    this.clonedProducts[rowdata.idAuto] = { ...rowdata };
    console.log("edited:", rowdata);
  }

  onRowEditSave(rowdata, index: number) {
    this.commonRes.updateMoqData(rowdata, this.season, this.channel, this.sessionStorageValue).subscribe((response) => {
      if (response) {
        this.getMoqTableData = response.result.moqData.data;
        this.responseMessage = response.message;
        this.hideMessage = true;
        console.log('response is...', response)
      }
    }, error => {
      if (error) {
        this.responseMessage = error.message;
        this.hideMessage = true;
        console.log('error is..', error)
      }
    })
    console.log("saved:", rowdata);
  }

  onRowEditCancel(rowdata, index: number) {
    this.getMoqTableData[index] = this.clonedProducts[rowdata.idAuto];
    delete this.clonedProducts[rowdata.idAuto];
  }

  uploadMoqListData(files: File[]) {
    //this.hideMessage = true;

    if (files) {
      this.commonRes.uploadMoqList(files, this.season, this.channel, this.sessionStorageValue).subscribe((res) => {
        if (res) {
          this.getMoqTableData = res.result.moqData.data;
          this.responseMessage = res && res.message;
          this.hideMessage = true;
          //this.callMoqDataApi();
          console.log('res is...', res)
        }
      },
        (error) => {
          if (error) {
            this.responseMessage = error && error.message;
            this.hideMessage = true;
            console.log('error is...', error)
          }
        });
    }
    else {
      console.log('File is not selected')
    }
  }

  uploadPreBuyData(files: File[]) {
    this.hideMessage = true;

    if (files) {
      this.commonRes.uploadPreBuyList(files, this.season, this.channel, this.sessionStorageValue).subscribe((res) => {
        if (res) {
          this.responseMessage = res && res.message
          console.log('res is...', res)
        }
      },
        (error) => {
          if (error) {
            this.responseMessage = error && error.message
            console.log('error is...', error)
          }
        });
    }
    else {
      console.log('File is not selected')
    }
  }

  get valueWithKeys() {
    return Object.keys(this.getMoqUpperTable);
  }

  getSelectedValue(status:Boolean,value:String){

    if(status){
      this.checkedList.push(value);  
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