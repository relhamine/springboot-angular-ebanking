import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PIndexComponent } from './c-index/p-index.component';
import { PEditComponent } from './c-edit/p-edit.component';
import { PAddComponent } from './c-add/p-add.component';
import {AListComponent} from "../account/a-list/a-list.component";

const routes: Routes = [
  {path : '', component: PIndexComponent},
  {path : 'edit/:id', component: PEditComponent},
  {path : 'accounts/:id', component: AListComponent},
  {path : 'add', component: PAddComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
