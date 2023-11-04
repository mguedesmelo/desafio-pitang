import { DatePipe, formatDate } from '@angular/common';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { UsersService } from './../service/users.service';

import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserResolver implements Resolve<User> {
  constructor(private service: UsersService) {
    // Empty
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<User> {
    if (route.params && route.params['id']) {
      return this.service.findById(route.params['id']);
    }
    const systemDate = formatDate(new Date(), 'MM/dd/yyyy', 'en_US');
    return of(
      {
        id: '',
        firstName: 'qqq',
        lastName: 'aaa',
        email: 'qqq@gmail.com',
        birthDay: systemDate,
        login: 'qqq',
        password: 'qqq',
        phone: '81999491213',
        cars: []
      });
  }
}
