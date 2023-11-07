import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UsersService } from '../../service/users.service';

@Component({
  selector: 'app-user-login-form',
  templateUrl: './user-login-form.component.html',
  styleUrls: ['./user-login-form.component.scss']
})
export class UserLoginFormComponent {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: UsersService
    ) {
    this.form = this.formBuilder.group({
      login: ['pinkman'],
      password: ['h3ll0'],
    });
  }

  onLogin() {
    const tt = this.service.signIn(this.form.value);
    console.log(this.service.getToken());
  }
}
