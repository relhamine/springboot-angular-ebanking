import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AlayoutComponent} from './alayout/alayout.component';
import {DashboardComponent} from './dashbord/dashbord.component';
import {AAddComponent} from "./account/a-add/a-add.component";

const routes: Routes = [
  {
    path: '', component: AlayoutComponent, children: [
      {path: '', redirectTo: 'dashbord', pathMatch: 'full'},
      {path: 'dashbord', component: DashboardComponent},
      {
        path: 'customer', loadChildren: () => import("./customer/customer.module").then(m => m.CustomerModule)
      },
      {
        path: 'account', loadChildren: () => import("./account/account.module").then(m => m.AccountModule)
      },
      {path: 'account/add', component: AAddComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  declarations: [],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
