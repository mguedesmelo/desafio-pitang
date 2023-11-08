import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { UsersService } from './users/service/users.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'car-rental-angular';

  constructor(
    private snackBar: MatSnackBar,
    private service: UsersService
  ) {
    // Empty
  }

  onAdd() {
    //this.router.navigate(['add'], { relativeTo: this.route });
  }

  logout() {
    this.service.logout();
  }

  cars() {
    this.openSnackBar('Navegar para tela de carros, recurso ainda não implementado');
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Ok', {
      duration: 5000
    });
  }

  isSignedIn() {
    return this.service.isSignedIn();
  }
}
