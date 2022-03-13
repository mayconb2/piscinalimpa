import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Calculation } from '../../model/calculationForm/calculation';
@Component({
  selector: 'app-calculation-result',
  templateUrl: './calculation-result.component.html',
  styleUrls: ['./calculation-result.component.css']
})
export class CalculationResultComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: Calculation) { }

  ngOnInit(): void {
  }
  
  // calculation: Calculation = {
  //   applicationSuggestions : [],
  //   hasMininumProducts: false
  // }

}
