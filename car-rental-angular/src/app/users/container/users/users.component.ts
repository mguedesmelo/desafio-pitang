import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { ErrorDialogComponent } from '../../../shared/components/error-dialog/error-dialog.component';
import { User } from '../../model/user';
import { UsersService } from '../../service/users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent {
  users$: Observable<User[]> | null = null;

  constructor(
    private usersService: UsersService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.refresh();
  }

  refresh() {
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
    this.router.navigate(['add'], { relativeTo: this.route });
  }

  onShowCars(user: User) {
    this.snackBar.open('Recurso temporariamente indisponível!', 'X', { duration: 5000 });
  }

  onEdit(user: User) {
      this.router.navigate(['edit', user.id], { relativeTo: this.route });
  }

  onDelete(user: User) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: `Deseja realmente remover o usuário ${user.firstName} ${user.lastName}?`,
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.usersService.delete(user).subscribe(
          () => {
            this.refresh();
            this.snackBar.open('Usuário removido com sucesso!', 'X', { duration: 5000 });
          },
          error => this.onError('Erro ao remover o curso')
        );
      }
    });
  }
}
