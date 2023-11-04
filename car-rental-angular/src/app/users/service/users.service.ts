import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { first, delay, tap } from 'rxjs';

import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  //private readonly API = '/assets/users.json';
  private readonly API = 'api/users';

  constructor(private httpClient: HttpClient) {
    // Empty
  }

  findAll(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.API)
    .pipe(
      first(),
      //delay(3000),
      //tap(user => console.log(user))
    );
  }

  findById(id: string) {
    return this.httpClient.get<User>(this.API + '/' + id).pipe(first());
  }

  save(user: Partial<User>) {
    console.log('users.service.save');
    if (user.id) {
      console.log('user.service.update');
      return this.update(user);
    }
    console.log('user.service.create');
    return this.create(user);
  }

  private create(user: Partial<User>) {
    return this.httpClient.post<User>(this.API, user).pipe(first());
  }

  private update(user: Partial<User>) {
    return this.httpClient.put<User>(`${this.API}/${user.id}`, user).pipe(first());
  }

  delete(user: Partial<User>) {
    console.log('users.service.delete');
    return this.httpClient.delete(`${this.API}/${user.id}`).pipe(first());
  }
}
