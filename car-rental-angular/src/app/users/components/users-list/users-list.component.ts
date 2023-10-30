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
  readonly displayedColumns = ['login', 'firstName', 'lastName', 'email', 'phone', 'actions'];

  constructor() {
    // Empty
  }

  onAdd() {
    this.add.emit(true);
    //this.router.navigate(['add'], {relativeTo: this.route});
  }
}
