import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ErrorComponent} from './components/errors/404/error.component';
import {AlayoutComponent} from "./components/alayout/alayout.component";
import {DashboardComponent} from "./components/dashbord/dashbord.component";
import {AAddComponent} from "./components/account/a-add/a-add.component";

const routes: Routes = [
  {
    path: '', component: AlayoutComponent, children: [
      {path: '', redirectTo: 'dashbord', pathMatch: 'full'},
      {path: 'dashbord', component: DashboardComponent},
      {
        path: 'customer', loadChildren: () => import("./components/customer/customer.module").then(m => m.CustomerModule)
      },
      {
        path: 'account', loadChildren: () => import("./components/account/account.module").then(m => m.AccountModule)
      },
      {path: 'account/add', component: AAddComponent},
      {
        path : '**', component : ErrorComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
