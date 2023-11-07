import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { first, delay, tap } from 'rxjs';

import { User } from '../model/user';
import { Login } from '../model/login';

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

  signIn(user: Partial<User>) {
    this.httpClient.post('api/signin', user)
    .pipe(
      catchError((error) => {
        return throwError(error); // Propaga o erro
      }))
      .subscribe((response: any) => {
        const token = response.token; // Supondo que a resposta contenha um campo "token"
        this.saveToken(token);
      });
  }

  private saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  isSignedIn() {
    return this.getToken();
  }

  getToken() {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.clear();
  }

  //signIn(login: string, password: string) {
  signInOld(user: Partial<User>) {
    return this.httpClient.post('api/signin', user).pipe(
      catchError((error) => {
        return throwError(error); // Propaga o erro
      })
    )
    .subscribe((response: any) => {
      const token = response.token; // Supondo que o token está no corpo da resposta
      return token;
      // Faça algo com o token, como armazená-lo para uso futuro.
    });
    /*
    return this.httpClient.post<string>('api/signin', user)
      .pipe(
        catchError((error) => {
          console.error('Erro na chamada GET:', error);
          return throwError(error);
        })
      )
      .subscribe((response: any) => {
        console.log(response);
      });
      */
  }

  /*
  signIn(user: Partial<User>) {
    this.http.get('https://api.example.com/data')
    .pipe(
      catchError((error) => {
        console.error('Erro na chamada GET:', error);
        return throwError(error); // Propaga o erro
      })
    )
    .subscribe((data) => {
      console.log('Dados recebidos:', data);
      // Faça algo com os dados, como atualizar o estado da aplicação ou exibir na interface do usuário.
    });
    return this.httpClient.get<string>(this.API, user).pipe(
      catchError((error) => {
        console.error('Erro na chamada GET:', error);
        return throwError(error); // Propaga o erro
      })
    ).subscribe((data) => {
      console.log('Dados recebidos:', data);
      // Faça algo com os dados, como atualizar o estado da aplicação ou exibir na interface do usuário.
    });
  }
  */

  findById(id: string) {
    return this.httpClient.get<User>(`${this.API}/${id}`).pipe(first());
  }

  save(user: Partial<User>) {
    console.log(user);
    if (user.id) {
      return this.update(user);
    }
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
