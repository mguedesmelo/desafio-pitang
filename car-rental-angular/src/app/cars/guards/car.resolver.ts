import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';

import { Car } from '../model/car';
import { CarsService } from '../service/cars.service';

@Injectable({
  providedIn: 'root'
})
export class CarResolver implements Resolve<Car> {
  constructor(private service: CarsService) {
    // Empty
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Car> {
    if (route.params && route.params['id']) {
      return this.service.findById(route.params['id']);
    }
    return of(
      {
        id: '',
        year: '',
        licensePlate: '',
        model: '',
        color: '',
      });
  }
}
