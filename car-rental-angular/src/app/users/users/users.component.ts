import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { ActivatedRoute, Router } from '@angular/router';
import { ErrorDialogComponent } from '../../shared/components/error-dialog/error-dialog.component';
import { User } from '../model/user';
import { UsersService } from './../service/users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent {
  users$: Observable<User[]>;
  //displayedColumns = ['login', 'firstName', 'lastName', 'email', 'phone', 'actions'];

  constructor(
    private usersService: UsersService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.users$ = this.usersService.findAll().pipe(
      catchError((error: HttpErrorResponse) => {
        this.onError('Erro ao carregar os usuários');
        return of([]);
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg,
    });
  }

  onAdd() {
    this.router.navigate(['add'], {relativeTo: this.route});
  }
}
