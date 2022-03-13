import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Common } from '../../common/common';
import { Product } from './product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ProductService {

  constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

  showMessage(msg: string) {
    this.snackBar.open(msg, 'x', {
      duration: 2500,
      horizontalPosition: "right",
      verticalPosition: "top"
    });
  }

  create(product: Product): Observable<Product> {

    let headers = new HttpHeaders();

    let httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json',
      'Authorization': Common.JWT })
    };

    headers.append('Authorization', 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXljb25iMiIsImV4cCI6MTY0Njg5MjQwNX0.fgESQEMoqeXmmedICVQCi3RBwrRRUnql9henY2kQ8QwzOAmYErz0Q67sAl-gyedtmy6g1dQ7w80NybhuuYaMZA');

    return this.http.post<Product>(Common.BASE_URL + '/adm/v1/product', product, httpOptions);
  }

  getProducts() : Observable<Product[]> {
    return this.http.get<Product[]>(Common.BASE_URL + '/api/v1/product');
  }

}