import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';

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
    private location: Location
    //private service: UsersService,
  ) {
    this.form = this.formBuilder.group({
      firstName: [null],
      lastName: [null],
      email: [null],
      birthDay: [null],
      lastLogin: [null],
      login: [null],
      phone: [null],
    });
  }

  onSave() {
    console.log(this.form.value);
    this.onError(this.form.value);
    /*
    this.service.save(this.form.value)
      .subscribe(
        result =>
        {
          this.onSuccess();
        },
        error => {
          this.onError(error);
        });
        */
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Curso salvo com sucesso!', '', { duration: 5000 });
    this.location.back();
  }
  private onError(error: any) {
    this.snackBar.open('Erro ao salvar o curso - ' + error.message, '', { duration: 5000 });
  }
}
