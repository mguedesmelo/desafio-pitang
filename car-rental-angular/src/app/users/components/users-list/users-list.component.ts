import { Component, EventEmitter, Input, Output } from '@angular/core';
import { User } from '../../model/user';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent {
  @Input() users: User[] = [];
  @Output() add = new EventEmitter(false);
  @Output() edit = new EventEmitter(false);
  @Output() delete = new EventEmitter(false);
  @Output() showCars = new EventEmitter(false);
  readonly displayedColumns = ['login', 'firstName', 'lastName', 'email', 'phone', 'actions'];

  constructor() {
    // Empty
  }

  onAdd() {
    console.log('user-list.component.onAdd');
    this.add.emit(true);
  }

  onEdit(user: User) {
    this.edit.emit(user);
  }

  onShowCars(user: User) {
    this.showCars.emit(user);
  }

  onDelete(user: User) {
    console.log('users.list.component.onDelete')
    this.delete.emit(user);
  }
}
