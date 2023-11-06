import { Component, OnInit, ViewChild } from '@angular/core';
import { RestService } from '../services/rest.service';

@Component({
    selector: 'app-sku-change',
    templateUrl: './sku-change.component.html'
})
export class SkuChangeComponent implements OnInit {

    constructor(private commonRes: RestService) { this.checkedList = []; }

    hrefUrl = this.commonRes.REST_BASE_URL;
    sessionStorageValue = sessionStorage.getItem('token');
    getSkuUpperTable = <any>[];
    getSkuTableData = <any>[];
    displayedColumnsHeading: string[];
    clonedProducts = {};
    dropList: string[];
    getSeasonList;
    getChannelList;
    oldSKU: any;
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
        this.callSkuDataApi();
        /* end */
    }

    callSkuDataApi() {
        this.commonRes.getSkuChangeData(this.season, this.channel).subscribe(
            (response) => {
                this.getSkuUpperTable = response.result.overviewData.data;
                this.getSkuTableData = response.result.skuchangeData.data;
                this.getSeasonList = response.result.seasons;
                this.getChannelList = response.result.channels;
                this.displayedColumnsHeading = response.result.skuchangeData.columns;
                this.dropList = ["true", "false"];
                this.getSeasonArray = response.result.seasonsArray;

                if (this.season == "" && this.channel == "") {
                    this.season = this.getSeasonList[0];
                    this.channel = this.getChannelList[0];
                }

                console.log("SKUData0", this.getSkuTableData);
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
        this.commonRes.updateSkuData(rowdata, this.season, this.channel, this.sessionStorageValue).subscribe((response) => {
            if (response) {
                this.getSkuTableData = response.result.skuchangeData.data;
                this.responseMessage = response && response.message;
                this.hideMessage = true;
                console.log('response is...', response)
            }
        }, error => {
            if (error) {
                this.responseMessage = error && error.message;
                this.hideMessage = true;
                console.log('error is..', error)
            }
        })
        console.log("saved:", rowdata);
    }

    onRowEditCancel(rowdata, index: number) {
        this.getSkuTableData[index] = this.clonedProducts[rowdata.idAuto];
        delete this.clonedProducts[rowdata.idAuto];
    }

    uploadSkuListData(files: File[]) {
        //this.hideMessage = true;

        if (files) {
            this.commonRes.uploadSkuList(files, this.season, this.channel, this.sessionStorageValue).subscribe((res) => {
                if (res) {
                    this.responseMessage = res && res.message;
                    this.getSkuTableData = res.result.skuchangeData.data;
                    this.hideMessage = true;
                   // this.callSkuDataApi();
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

    get valueWithKeys() {
        return Object.keys(this.getSkuUpperTable);
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
