import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UsersService } from './users/service/users.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'car-rental-angular';
  userLogin: string | null = '';
  signedIn: boolean = false;

  constructor(
    private snackBar: MatSnackBar,
    private service: UsersService
  ) {
    this.userLogin = this.service.getLogin();
    this.service.isSignedInEmitter.subscribe(
      signed => this.signedIn = signed
    );
  }

  logout() {
    this.service.logout();
  }

  cars() {
    this.openSnackBar('Navegar para tela de carros do usuário logado, recurso ainda não implementado');
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'X', {
      duration: 5000
    });
  }

  isSignedIn() {
    return this.signedIn;
    //return this.service.isSignedIn();
  }
}
