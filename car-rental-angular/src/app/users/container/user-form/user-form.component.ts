import { Location } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
      firstName: [user.firstName, [Validators.required, Validators.minLength(5), Validators.maxLength(60)]],
      lastName: [user.lastName, [Validators.required, Validators.minLength(5), Validators.maxLength(60)]],
      email: [user.email, [Validators.required, Validators.email, Validators.maxLength(255)]],
      birthDay: [new Date(user.birthDay), [Validators.required]],
      login: [user.login, [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
      password: [user.password, [Validators.required, Validators.minLength(5), Validators.maxLength(30)]],
      phone: [user.phone, [Validators.required, Validators.maxLength(20)]],
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

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }
    if (field?.hasError('minlength')) {
      const requiredLength: number = field.errors ? field.errors['minlength']['requiredLength'] : 5;
      return `Tamaanho mínimo precisa ser de ${requiredLength} caracteres`;
    }
    if (field?.hasError('maxlength')) {
      const requiredLength: number = field.errors ? field.errors['maxlength']['requiredLength'] : 60;
      return `Tamaanho máximo excedido de ${requiredLength} caracteres`;
    }
    if (field?.hasError('email')) {
      return 'E-mail inválido';
    }
    return 'Campo inválido';
    //return 'email';// this.form.get('email').hasError('email' ? 'Not a valid email' : '';
  }

  private onSuccess() {
    this.snackBar.open('Usuário salvo com sucesso!', 'X', { duration: 5000 });
    this.location.back();
  }
  private onError(error: HttpErrorResponse) {
    console.log(error);
    this.snackBar.open(error.message, '', { duration: 5000 });
  }
}
