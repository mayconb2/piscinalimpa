import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ApplicationForm } from '../../model/applicationForm/applicationForm';
import { Product } from '../../model/product/product';
import { ProductService } from '../../model/product/product.service';

@Component({
  selector: 'app-calculation-form',
  templateUrl: './calculation-form.component.html',
  styleUrls: ['./calculation-form.component.css']
})
export class CalculationFormComponent implements OnInit {

  productsFromBackend: Product[] = [];

  productsAffectedParam1: Product[] = [];
  productsAffectedParam2: Product[] = [];
  productsAffectedParam3: Product[] = [];
  productsAffectedParam4: Product[] = [];

  selectedProducts: Product[] = [];
  

  pruduct: Product = {
    name: 'Elevador Ph',
    affectedParameter: 2,
    brandId: 1,
    brandName: "xunda"
  }

  applicationForm: ApplicationForm = {
    volume: 0,
    products: [],
    parametersValues: []
  }

  constructor(private productService: ProductService,
    private http: HttpClient) { }

  ngOnInit(): void {

    let allProductsFromBack : Product[] = []; 
    
    this.productService.getProducts()
      .subscribe(products => {
        allProductsFromBack.push(...products);
        // console.log(allProductsFromBack)
        this.productsFromBackend = products;
        this.fillProductsByParameter(allProductsFromBack);
      });
  }

  showMessage() : void {
    this.productService.showMessage("Deu liga")
  }

  createProduct() {
    this.productService.create(this.pruduct).subscribe(() => {
      this.showMessage()
    })
  }

  private fillProductsByParameter(allProductsFromBack: Product[]): void {
    this.productsAffectedParam1.push(...allProductsFromBack.filter(products => products.affectedParameter === 1));
    this.productsAffectedParam2.push(...allProductsFromBack.filter(products => products.affectedParameter === 2));
    this.productsAffectedParam3.push(...allProductsFromBack.filter(products => products.affectedParameter === 3));
    this.productsAffectedParam4.push(...allProductsFromBack.filter(products => products.affectedParameter === 4));
  }
}
