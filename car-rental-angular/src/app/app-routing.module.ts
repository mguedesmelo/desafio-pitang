import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'users'
  },
  {
    path: 'users',
    loadChildren: () => import('./users/users.module').then(u => u.UsersModule)
  },
  {
    path: 'cars',
    loadChildren: () => import('./cars/cars.module').then(u => u.CarsModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
