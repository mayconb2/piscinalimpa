import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Product } from '../../product/product';
import { ProductService } from '../../product/product.service';
import { Common } from '../../common/common';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-calculation-form',
  templateUrl: './calculation-form.component.html',
  styleUrls: ['./calculation-form.component.css']
})
export class CalculationFormComponent implements OnInit {

  pruduct: Product = {
    name: 'Elevador Ph',
    affectedParameter: 2,
    brand: 1
  }

  constructor(private productService: ProductService,
    private http: HttpClient) { }

  ngOnInit(): void {
  }

  showMessage() : void {
    this.productService.showMessage("Deu liga")
  }

  createProduct() {
    this.productService.create(this.pruduct).subscribe(() => {
      this.showMessage()
    })

  }
}
