import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from '@angular/router';
import { Observable, catchError, of } from 'rxjs';

import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Car } from '../model/car';
import { CarsService } from '../service/cars.service';

@Injectable({
  providedIn: 'root'
})
export class CarResolver implements Resolve<Car> {
  constructor(
    private service: CarsService,
    private snackBar: MatSnackBar,
    private router: Router
    ) {
    // Empty
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Car> {
    if (route.params && route.params['id']) {
      return this.service.findById(route.params['id']).pipe(
        catchError((error: HttpErrorResponse) => {
          this.onError(error);
          return of({
            id: '',
            year: '',
            licensePlate: '',
            model: '',
            color: '',
          });
        })
      );
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

  onError(error: HttpErrorResponse) {
    this.router.navigate(['/users/login']);
    this.snackBar.open('Para acessar esta área você deve estar logado', 'X', {
      duration: 5000
    });
  }
}
