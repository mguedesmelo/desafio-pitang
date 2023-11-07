import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';

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

  onAdd() {
    //this.router.navigate(['add'], { relativeTo: this.route });
  }

  notReady(msg: string) {
    this.openSnackBar('Logout, recurso ainda não implementado');
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Ok', {
      duration: 5000
    });
  }
}
