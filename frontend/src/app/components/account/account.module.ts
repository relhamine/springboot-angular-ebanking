import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountRoutingModule } from './account-routing.module';
import { AIndexComponent } from './a-index/a-index.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {AListComponent} from "./a-list/a-list.component";
import {AAddComponent} from "./a-add/a-add.component";
@NgModule({
  declarations: [
    AIndexComponent,
    AListComponent,
    AAddComponent
  ],
  imports: [
    CommonModule,
    AccountRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AccountModule { }
