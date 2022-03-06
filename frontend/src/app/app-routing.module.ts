import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './views/home/home.component';
import { CalculationFormComponent } from './views/calculation-form/calculation-form.component';

const routes: Routes = [{
  path:"",
  component: HomeComponent
  },
  {
    path:"calculation",
    component: CalculationFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
