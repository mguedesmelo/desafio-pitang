import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UsersService } from '../../service/users.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-user-login-form',
  templateUrl: './user-login-form.component.html',
  styleUrls: ['./user-login-form.component.scss']
})
export class UserLoginFormComponent {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private service: UsersService,
    private snackBar: MatSnackBar,
    ) {
    this.form = this.formBuilder.group({
      login: ['pinkman'],
      password: ['h3ll0'],
    });
  }

  onLogin() {
    this.service.signIn(this.form.value).subscribe(
      () =>
      {
        this.onSuccess();
      },
      (error: HttpErrorResponse) => {
        this.onError(error);
      });;

  }

  private onSuccess() {
    this.router.navigate(['/']);
    this.snackBar.open('Bem-vindo ao sistema!', 'X', { duration: 5000 });
  }

  private onError(error: HttpErrorResponse) {
    this.snackBar.open(error.message, 'X', { duration: 5000 });
  }
}
