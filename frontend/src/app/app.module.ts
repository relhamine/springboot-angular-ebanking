import {ErrorHandler, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ErrorComponent} from './components/errors/404/error.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {NgbModule, NgbPaginationModule} from '@ng-bootstrap/ng-bootstrap';
import {GlobalErrorHandler} from "./core/config/errors/global-error-handler";
import {ServerErrorInterceptor} from "./core/config/errors/server-error.interceptor";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MessagesComponent} from "./components/message/messages.component";
import {MessageService} from "./services/message.service";
import {AlayoutComponent} from "./components/alayout/alayout.component";
import {DashboardComponent} from "./components/dashbord/dashbord.component";
import {SidemenuComponent} from "./components/sidemenu/sidemenu.component";
import {AheaderComponent} from "./components/aheader/aheader.component";


@NgModule({
  declarations: [
    AlayoutComponent,
    DashboardComponent,
    SidemenuComponent,
    AheaderComponent,
    AppComponent,
    ErrorComponent,
    MessagesComponent
  ],
  imports: [

    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbPaginationModule,
    NgbModule
  ],
  providers: [
    MessageService,
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandler
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ServerErrorInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
