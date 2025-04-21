import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayoutComponent } from './playout/playout.component';

const routes: Routes = [
  {
    path: '',component: PlayoutComponent, children : [
      {path: "", redirectTo: "home", pathMatch: "full"}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PublicRoutingModule { }
