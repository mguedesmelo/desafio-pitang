import { HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BaseService {
  constructor() {
    // Empty
  }

  protected saveUserInfo(token: string, login: string) {
    localStorage.setItem('token', token);
    localStorage.setItem('login', login);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('login');
    localStorage.clear();
  }

  isSignedIn() {
    if (this.getToken()) {
      return true;
    }
    return false;
  }

  getLogin(): string | null {
    if (this.isSignedIn()) {
      return localStorage.getItem('login');
    }
    return '';
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getServerErrorMessage(error: HttpErrorResponse): string {
    let toReturn: string;
    if (error.error instanceof ErrorEvent) {
        toReturn = `Error: ${error.error.message}`;
    } else {
      switch (error.status) {
        case 403: {
          toReturn = `Erro: ${error.status} - ${error.statusText}`;
          break;
        }
        case 404: {
          toReturn = `Not Found: ${error.status} - ${error.statusText}`;
          break;
        }
        case 500: {
          toReturn = `Internal Server Error: ${error.status} - ${error.statusText}`;
          break;
        }
        default: {
          toReturn = `Unknown Server Error: ${error.status} - ${error.statusText}`;
          break;
        }
      }
    }
    return toReturn;
  }
}
