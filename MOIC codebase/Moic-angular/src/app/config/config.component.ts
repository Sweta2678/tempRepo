import { Component, OnInit, ViewChild } from '@angular/core';
import { RestService } from '../services/rest.service';

@Component({
    selector: 'app-config',
    templateUrl: './config.component.html',
    providers: []
})
export class ConfigComponent implements OnInit {

    constructor(private commonRes: RestService) { }

    hrefUrl = this.commonRes.REST_BASE_URL;
    sessionStorageValue = sessionStorage.getItem('token');
    getConfigTableData = <any>[];
    displayedColumnsHeading: string[];
    getChannelList: string[];
    getSegmentList: string[];
    getRetailList: string[];
    getWholesaleList: string[];
    clonedProducts = {};
    soldToNumber: any;
    soldToDescription: any;
    booleanValue: any;
    responseMessage: any;
    hideMessage: boolean;
    first = 0;
    rows = 10;
    Object = Object;

    @ViewChild('dt') dt: any;

    ngOnInit(): void {

        /*to get data from rest service url----------------start */
        this.callConfigDataApi();
        /* end */
    }

    callConfigDataApi() {
        this.commonRes.getConfigData().subscribe(
            (response) => {
                this.getConfigTableData = response.result.data;
                this.displayedColumnsHeading = response.result.columns;
                this.getChannelList = response.result.channels;
                this.getSegmentList = response.result.segments;
                this.getRetailList = response.result.Retail;
                this.getWholesaleList = response.result.Wholesale;
                this.booleanValue = ["true", "false"];
                console.log("RetailList:", this.getRetailList);
            },
            (error) => {
                console.log("Error occured :" + error);
            }
        )
    }

    onRowEditInit(rowdata, index: number) {
        this.clonedProducts[rowdata.index] = { ...rowdata };
        console.log("edited:", this.clonedProducts);
    }

    onRowEditSave(rowdata, sessionStorageValue) {
        this.commonRes.updateConfigData(rowdata, this.sessionStorageValue).subscribe((response) => {
            if (response) {
                this.getConfigTableData = response.result.data;
                this.responseMessage = response.message;
                this.hideMessage = true;
                console.log('response is...', this.responseMessage)
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
        this.getConfigTableData[index] = this.clonedProducts[rowdata.index];
        delete this.clonedProducts[rowdata.index];
    }

    uploadCustData(files: File[]) {
        //this.hideMessage = true;
        //this.fadeOutLink();

        if (files) {
            this.commonRes.uploadCustomerFile(files, this.sessionStorageValue).subscribe((res) => {
                if (res) {
                    this.responseMessage = res && res.message;
                    this.getConfigTableData = res.result;
                    this.hideMessage = true;
                    //this.callConfigDataApi();
                    console.log('res is...', res)
                }
            },
                (error) => {
                    if (error) {
                        this.responseMessage = error && error.message
                        this.hideMessage = true;
                        console.log('error is...', error)
                    }
                });
        }
        else {
            console.log('File is not selected')
        }
    }

}

