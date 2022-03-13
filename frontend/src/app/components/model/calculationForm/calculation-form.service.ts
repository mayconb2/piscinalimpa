import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ApplicationForm } from './applicationForm';
import { Common } from '../../common/common';
import { Observable } from 'rxjs';
import { ApplicationSuggestions } from './applicationSuggestions';
import { Calculation } from './calculation';

@Injectable({
  providedIn: 'root'
})
export class CalculationFormService {

  constructor(private http: HttpClient) { }

  calculateProducts(applicationForm: ApplicationForm) : Observable<Calculation> {
    return this.http.post<Calculation>(Common.BASE_URL + '/api/v1/calculation', applicationForm);
  }
}
