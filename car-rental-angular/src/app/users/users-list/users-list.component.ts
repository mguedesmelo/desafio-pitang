import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/user';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent {
  @Input() users: User[] = [];
  readonly displayedColumns = ['login', 'firstName', 'lastName', 'email', 'phone', 'actions'];

  constructor(
    private router: Router,
    private route: ActivatedRoute
  ) {
    // Empty
  }

  onAdd() {
    this.router.navigate(['add'], {relativeTo: this.route});
  }
}
