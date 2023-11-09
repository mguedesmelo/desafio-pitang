import { HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BaseService {
  constructor() {
    // Empty
  }

  protected saveToken(token: string) {
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
