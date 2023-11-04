import { Location } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../model/user';
import { UsersService } from '../../service/users.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss'],
})
export class UserFormComponent {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: UsersService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
  ) {
    const user: User = this.route.snapshot.data['user'];
    console.log('user-form.component.constructor');
    console.log(user);
    this.form = this.formBuilder.group({
      id: user.id,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      birthDay: new FormControl(new Date(user.birthDay)),
      login: user.login,
      password: user.password,
      phone: user.phone,
    });
    if (user.id) {
      this.form.controls['password'].disable();
    }
  }

  onSave() {
    console.log('user-form.component.save');
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
    this.snackBar.open('Usuário salvo com sucesso!', 'X', { duration: 5000 });
    this.location.back();
  }
  private onError(error: HttpErrorResponse) {
    console.log(error);
    this.snackBar.open(error.message , '', { duration: 5000 });
  }
}
