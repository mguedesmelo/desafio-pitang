import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { first, delay, tap } from 'rxjs';

import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private readonly API = '/assets/users.json';

  constructor(private httpClient: HttpClient) {
    // Empty
  }

  findAll(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.API)
    .pipe(
      first(),
      //delay(3000),
      tap(user => console.log(user))
    );
    /*
    return [
      {
        id: '1',
        firstName: 'Marcio',
        lastName: 'Melo',
        email: 'mguedesmelo@gmail.com',
        birthDay: '06-06-1974',
        lastLogin: '27-10-2023',
        login: 'mguedesmelo',
        phone: '+5581999491213'
      }
    ];
    */
  }
}
