import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ApplicationForm } from './applicationForm';
import { Common } from '../../common/common';
import { Observable } from 'rxjs';
import { ApplicationSugestion } from './applicationSugestion';

@Injectable({
  providedIn: 'root'
})
export class CalculationFormService {

  constructor(private http: HttpClient) { }

  calculateProducts(applicationForm: ApplicationForm) : Observable<ApplicationSugestion> {
    return this.http.post<ApplicationSugestion>(Common.BASE_URL + '/api/v1/calculation', applicationForm);
  }
}
