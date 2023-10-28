import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UsersService } from '../service/users.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private location: Location,
    private service: UsersService,
  ) {
    this.form = this.formBuilder.group({
      firstName: 'Skyler',
      lastName: 'White',
      email: 'skyler@somedomain.com',
      birthDay: '1970-11-08',
      login: 'skyler',
      password: 'h3ll0',
      phone: '+1 804-406-4234',
    });
  }

  onSave() {
    this.service.save(this.form.value)
      .subscribe(
        result =>
        {
          this.onSuccess();
        },
        error => {
          this.onError(error);
        });
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Usuário salvo com sucesso!', '', { duration: 5000 });
    this.location.back();
  }
  private onError(error: HttpErrorResponse) {
    console.log(error);
    this.snackBar.open(error.error.message , '', { duration: 5000 });
  }
}
