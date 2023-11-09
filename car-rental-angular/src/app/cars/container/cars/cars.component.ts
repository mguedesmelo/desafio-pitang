import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, of } from 'rxjs';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Car } from 'src/app/cars/model/car';
import { CarsService } from '../../service/cars.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.scss']
})
export class CarsComponent {
  cars$: Observable<Car[]> | null = null;

  constructor(
    private carsService: CarsService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.refresh();
  }

  refresh() {
    this.cars$ = this.carsService.findAll().pipe(
      catchError((error: HttpErrorResponse) => {
        this.onError(error);
        return of([]);
      })
    );
  }

  onError(error: HttpErrorResponse) {
    this.router.navigate(['/users/login']);
    this.dialog.open(ErrorDialogComponent, {
      data: error.message,
    });
  }

  onAdd() {
    this.router.navigate(['add'], { relativeTo: this.route });
  }

  onEdit(car: Car) {
      this.router.navigate(['edit', car.id], { relativeTo: this.route });
  }

  onDelete(car: Car) {
    console.log('cars.component.onDelete');

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: `Deseja realmente remover o carro ${car.licensePlate}?`,
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        /*
        this.usersService.delete(user).subscribe(
          () => {
            this.refresh();
            this.snackBar.open('Usuário removido com sucesso!', 'X', { duration: 5000 });
          },
          error => this.onError('Erro ao remover o curso')
        );
        */
      }
    });
  }
}
