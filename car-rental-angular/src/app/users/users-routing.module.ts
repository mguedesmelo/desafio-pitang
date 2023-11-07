import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserFormComponent } from './container/user-form/user-form.component';
import { UsersComponent } from './container/users/users.component';
import { UserResolver } from './guards/user.resolver';
import { UserLoginFormComponent } from './container/user-login-form/user-login-form.component';

const routes: Routes = [
  { path: '', component: UsersComponent },
  { path: 'login', component: UserLoginFormComponent },
  { path: 'add', component: UserFormComponent, resolve: { user: UserResolver } },
  { path: 'edit/:id', component: UserFormComponent, resolve: { user: UserResolver } },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule {
  // Empty
}
