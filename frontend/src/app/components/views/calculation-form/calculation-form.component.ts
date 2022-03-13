import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ApplicationForm } from '../../model/calculationForm/applicationForm';
import { ParameterValue } from '../../model/parameterValue/parameterValue';
import { Product } from '../../model/product/product';
import { ProductService } from '../../model/product/product.service';
import { FormControl, FormBuilder, Validators } from '@angular/forms';
import { CalculationFormService } from '../../model/calculationForm/calculation-form.service';

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

  parameterValues: ParameterValue[] = [];
  

  pruduct: Product = {
    name: 'Elevador Ph',
    affectedParameter: 2,
    brandId: 1,
    brandName: "xunda"
  }

  applicationForm: ApplicationForm = {
    volume: 0,
    products: [],
    parameters: []
  }

  constructor(private productService: ProductService,
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private calculationService: CalculationFormService) { }

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

  formApplication = this.formBuilder.group({
    volume:[null, Validators.required],
    chlorine: [null, Validators.required],
    ph: [null, Validators.required],
    alkalinity: [null, Validators.required],
    turbidity: [null, Validators.required],
    productOne: [null, Validators.required],
    productTwo: [null, Validators.required],
    productThree: [null, Validators.required],
    productFour: [null, Validators.required]
  })

  get chlorine() {
    return this.formApplication.get('chlorine')
  }

  get ph() {
    return this.formApplication.get('ph')
  }

  get alkalinity() {
    return this.formApplication.get('alkalinity')
  }

  get turbidity() {
    return this.formApplication.get('turbidity')
  }

  get productOne() {
    return this.formApplication.get('productOne')
  }

  get productTwo() {
    return this.formApplication.get('productTwo')
  }

  get productThree() {
    return this.formApplication.get('productThree')
  }

  get productFour() {
    return this.formApplication.get('productFour')
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

  prepareToCalculateAndCalculate() {
    let form = this.formApplication.value;

    let productsIds: number[] = [];

    productsIds.push(form.productOne);
    productsIds.push(form.productTwo);
    productsIds.push(form.productThree);
    productsIds.push(form.productFour);

    let paramsValues: number[] = [];

    paramsValues.push(form.chlorine);
    paramsValues.push(form.ph);
    paramsValues.push(form.alkalinity);
    paramsValues.push(form.turbidity);

    this.findProductAndPopulate(productsIds);
    this.populateParams(paramsValues);
    this.applicationForm.volume = form.volume;

    this.calculate();

  }

  private findProductAndPopulate(productsIds: number[]): void {
    
    let productsFromForm: Product[] = [];
    
    productsIds.forEach(id => {
      productsFromForm.push(...this.productsFromBackend.filter(productMap => productMap.id == id));
    })

    this.selectedProducts.push(...productsFromForm);
    this.applicationForm.products.push(...productsFromForm);
    
    
  }

  private populateParams(paramsValues: number[]) {

    const clorineValue: ParameterValue = {parameterId: 1, value: paramsValues[0]};
    const phValue: ParameterValue = {parameterId: 2, value: paramsValues[1]};
    const alkalinityValue: ParameterValue = {parameterId: 3, value: paramsValues[2]};
    const turbidityValue: ParameterValue = {parameterId: 4, value: paramsValues[3]};

    this.applicationForm.parameters.push(clorineValue);
    this.applicationForm.parameters.push(phValue);
    this.applicationForm.parameters.push(alkalinityValue);
    this.applicationForm.parameters.push(turbidityValue);

  }

  private calculate() {
    console.log(this.applicationForm);
    this.calculationService.calculateProducts(this.applicationForm)
      .subscribe(x => {
        console.log(x);
      })
  }
}
