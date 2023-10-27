import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { UsersService } from './../service/users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent {
  users: Observable<User[]>;
  displayedColumns = ['login', 'firstName', 'lastName', 'email', 'phone'];

  constructor(private usersService: UsersService) {
    this.users = this.usersService.findAll();
  }
}
