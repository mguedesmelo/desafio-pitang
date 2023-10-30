import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'car-rental-angular';

  constructor(
    private snackBar: MatSnackBar
  ) {
    // Empty
  }

  cars() {
    console.log('navegar para página de carros');
    this.openSnackBar('Navegar para tela de carros, recurso ainda não implementado');
  }

  logout() {
    console.log('efetuar logout');
    this.openSnackBar('Logout, recurso ainda não implementado');
  }

  openSnackBar(message: string) {
    this.snackBar.open(message);
  }
}
