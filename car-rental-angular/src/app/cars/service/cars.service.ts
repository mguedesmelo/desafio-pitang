import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, first } from 'rxjs';
import { Car } from 'src/app/cars/model/car';

@Injectable({
  providedIn: 'root'
})
export class CarsService {
  private readonly API = 'api/cars';

  constructor(private httpClient: HttpClient) {
    // Empty
  }

  findAll(): Observable<Car[]> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });
    return this.httpClient.get<Car[]>(this.API, { headers });
  }

  findById(id: string) {
    return this.httpClient.get<Car>(`${this.API}/${id}`).pipe(first());
  }

  save(car: Partial<Car>) {
    if (car.id) {
      return this.update(car);
    }
    return this.create(car);
  }

  private create(car: Partial<Car>) {
    return this.httpClient.post<Car>(this.API, car).pipe(first());
  }

  private update(car: Partial<Car>) {
    return this.httpClient.put<Car>(`${this.API}/${car.id}`, car).pipe(first());
  }

  delete(car: Partial<Car>) {
    return this.httpClient.delete(`${this.API}/${car.id}`).pipe(first());
  }
}
