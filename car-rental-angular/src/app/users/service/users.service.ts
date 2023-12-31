import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, first, map, throwError } from 'rxjs';

import { DatePipe } from '@angular/common';
import { BaseService } from 'src/app/shared/service/base.service';
import { User } from '../model/user';
import { UserToken } from '../model/user-token';

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

  signIn(user: Partial<User>) {
    return this.httpClient.post<UserToken>('api/signin', user)
    .pipe(
      map((token: UserToken) => {
        const userToken: UserToken = token;
        this.saveUserInfo(userToken.token, userToken.login);

        this.isSignedInEmitter.emit(true);

        return userToken;
      }),
      catchError(error => {
        let errorMsg: string = this.getServerErrorMessage(error);
        this.isSignedInEmitter.emit(false);
        return throwError(() => new Error(errorMsg));
      }),
    );
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
    user.birthDay = new DatePipe('en').transform(user.birthDay, 'MM/dd/yyyy')?.toString();
    if (user.id) {
      return this.update(user);
    }
    return this.create(user);
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
}
