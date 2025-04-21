import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AIndexComponent } from './a-index/a-index.component';
import {PEditComponent} from "../customer/c-edit/p-edit.component";
import {AListComponent} from "./a-list/a-list.component";

const routes: Routes = [
  {path : '', component: AIndexComponent},
  {path : 'accounts/:id', component: AListComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AccountRoutingModule { }
