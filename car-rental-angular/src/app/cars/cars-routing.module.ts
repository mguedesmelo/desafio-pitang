import { NgModule, Inject } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarFormComponent } from './container/car-form/car-form/car-form.component';
import { CarsComponent } from './container/cars/cars.component';
import { CarResolver } from './guards/car.resolver';
import { CarsService } from './service/cars.service';

const routes: Routes = [
  { path: '', component: CarsComponent },
  { path: 'add', component: CarFormComponent, resolve: { car: CarResolver } },
  { path: 'edit/:id', component: CarFormComponent, resolve: { car: CarResolver } },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarsRoutingModule { }
