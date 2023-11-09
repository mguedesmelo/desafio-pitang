import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, first, map, throwError } from 'rxjs';

import { User } from '../model/user';
import { UserToken } from '../model/user-token';
import { BaseService } from 'src/app/shared/service/base.service';

@Injectable({
  providedIn: 'root'
})
export class UsersService extends BaseService {
  //private readonly API = '/assets/users.json';
  private readonly API = 'api/users';

  constructor(private httpClient: HttpClient) {
    super();
  }

  findAll(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.API)
    .pipe(
      first(),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        return throwError(() => new Error(errorMsg));
      }),
      //delay(3000),
      //tap(user => console.log(user))
    );
  }

  login(user: Partial<User>) {
    return this.httpClient.post<UserToken>('api/signin', user)
    .pipe(
      map((token: UserToken) => {
        const userToken: UserToken = token;
        localStorage.setItem('token', userToken.token);

        return userToken;
      }),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        return throwError(() => new Error(errorMsg));
      }),
    );
  }

  signIn(user: Partial<User>) {
    return this.httpClient.post('api/signin', user)
    .pipe(
      catchError((error) => {
        return throwError(() => new Error(error));
      })
    )
    .subscribe((response: any) => {
      const token = response.token;
      this.saveToken(token);
    });
  }

  private saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  isSignedIn() {
    if (this.getToken()) {
      return true;
    }
    return false;
  }

  getToken() {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.clear();
  }

  findById(id: string) {
    return this.httpClient.get<User>(`${this.API}/${id}`).pipe(
      first(),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        return throwError(() => new Error(errorMsg));
      }),
    );
  }

  save(user: Partial<User>) {
    if (user.id) {
      return this.update(user);
    }
    return this.create(user);
  }

  private create(user: Partial<User>) {
    return this.httpClient.post<User>(this.API, user).pipe(
      first(),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        return throwError(() => new Error(errorMsg));
      }),
    );
  }

  private update(user: Partial<User>) {
    return this.httpClient.put<User>(`${this.API}/${user.id}`, user).pipe(
      first(),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        return throwError(() => new Error(errorMsg));
      }),
    );
  }

  delete(user: Partial<User>) {
    return this.httpClient.delete(`${this.API}/${user.id}`).pipe(
      first(),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        return throwError(() => new Error(errorMsg));
      }),
    );
  }
}
