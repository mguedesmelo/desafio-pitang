import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SharedModule } from '../shared/shared.module';
import { UserFormComponent } from './container/user-form/user-form.component';
import { UsersRoutingModule } from './users-routing.module';
import { UsersComponent } from './container/users/users.component';
import { UsersListComponent } from './components/users-list/users-list.component';

@NgModule({
  declarations: [
    UsersComponent,
    UserFormComponent,
    UsersListComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    AppMaterialModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class UsersModule { }
