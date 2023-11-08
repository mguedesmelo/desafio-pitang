import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarsComponent } from './container/cars/cars.component';

const routes: Routes = [
  { path: '', component: CarsComponent },
//  { path: 'add', component: CarFormComponent, resolve: { car: CarResolver } },
//  { path: 'edit/:id', component: CArFormComponent, resolve: { car: CarResolver } },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarsRoutingModule { }
