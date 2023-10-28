import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    //private service: UsersService,
    //private snackBar: MatSnackBar,
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

  }

  onCancel() {

  }
}
