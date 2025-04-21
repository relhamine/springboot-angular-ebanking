import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from './components/errors/404/error.component';
import { AuthGuard } from './_helpers/auth.guard';
import {DashboardComponent} from "./components/dashbord/dashbord.component";

const routes: Routes = [
  {
    path : '', loadChildren : () => import('./components/admin.module')
      .then(m => m.AdminModule)
  },
  {
    path : '**', component : ErrorComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
