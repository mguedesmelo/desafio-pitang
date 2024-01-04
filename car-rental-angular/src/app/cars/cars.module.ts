import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SharedModule } from '../shared/shared.module';
import { CarsRoutingModule } from './cars-routing.module';
import { CarsListComponent } from './components/cars-list/cars-list.component';
import { CarFormComponent } from './container/car-form/car-form/car-form.component';
import { CarsComponent } from './container/cars/cars.component';

@NgModule({
  declarations: [
    CarsListComponent,
    CarsComponent,
    CarFormComponent
  ],
  imports: [
    CommonModule,
    CarsRoutingModule,
    AppMaterialModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class CarsModule { }
