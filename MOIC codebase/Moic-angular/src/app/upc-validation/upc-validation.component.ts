import { Component, OnInit } from '@angular/core';
import { RestService } from '../services/rest.service';

@Component({
  selector: 'app-upc-validation',
  templateUrl: './upc-validation.component.html'
})
export class UpcValidationComponent implements OnInit {

  hrefUrl = this.commonRes.REST_BASE_URL;
  getSeasonList;
  season = "";

  getSeasonArray;
  showDropDown;
  checkedList : any[];
  checked =false;

  constructor(private commonRes: RestService) { this.checkedList = []; }

  ngOnInit(): void {
    this.commonRes.getUpcValData().subscribe(
      (response) => {
        this.getSeasonList = response.result.seasons;
        this.getSeasonArray = response.result.seasonsArray;

        if (this.season == "") {
          this.season = this.getSeasonList[0];
        }

      },
      (error) => {
        console.log("Error occured :" + error);
      }
    )

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
