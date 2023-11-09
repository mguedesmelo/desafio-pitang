import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, first, throwError } from 'rxjs';
import { Car } from 'src/app/cars/model/car';
import { BaseService } from 'src/app/shared/service/base.service';

@Injectable({
  providedIn: 'root'
})
export class CarsService extends BaseService {
  private readonly API = 'api/cars';

  constructor(private httpClient: HttpClient) {
    super();
  }

  findAll(): Observable<Car[]> {
    const headers = this.getHeaders();
    return this.httpClient.get<Car[]>(this.API, { headers }).pipe(
      first(),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        return throwError(() => new Error(errorMsg));
      })
    );
  }

  findById(id: string) {
    const headers = this.getHeaders();
    return this.httpClient.get<Car>(`${this.API}/${id}`, { headers }).pipe(
      first(),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        return throwError(() => new Error(errorMsg));
      })
    );
  }

  save(car: Partial<Car>) {
    if (car.id) {
      return this.update(car);
    }
    return this.create(car);
  }

  delete(car: Partial<Car>) {
    return this.httpClient.delete(`${this.API}/${car.id}`).pipe(
      first(),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        return throwError(() => new Error(errorMsg));
      })
    );
  }

  private create(car: Partial<Car>) {
    const headers = this.getHeaders();
    return this.httpClient.post<Car>(this.API, car, { headers }).pipe(
      first(),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        return throwError(() => new Error(errorMsg));
      })
    );
  }

  private update(car: Partial<Car>) {
    const headers = this.getHeaders();
    return this.httpClient.put<Car>(`${this.API}/${car.id}`, car, { headers }).pipe(
      first(),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        return throwError(() => new Error(errorMsg));
      })
    );
  }

  private getHeaders() {
    return new HttpHeaders({
      'Authorization': `Bearer ${this.getToken()}`
    });
  }
}
