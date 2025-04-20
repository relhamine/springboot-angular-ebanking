import {ErrorHandler, Injectable, inject} from '@angular/core';
import {NGXLogger} from "ngx-logger";
import {Router} from '@angular/router';
import {ErrorService} from "../../../services/errors/error.service";
import {NotificationService} from "../../../services/errors/notification.service";
import {LoggingService} from "../../../services/errors/logging.service";
import {HttpErrorResponse} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class GlobalErrorHandler implements ErrorHandler {
  // ===========[ DEPENDENCIES INJECTIONS ]===========
  private router = inject(Router);
  // private translationService = inject(TranslationHelperService);
  //private logger = inject(NGXLogger);
  //private alert = inject(AlertHelperService);

  private errorService = inject(ErrorService);
  private logger = inject(LoggingService);
  private notifier = inject(NotificationService);


  // ===========[ METHODS ]===========
  handleError(error: Error | HttpErrorResponse, context?: unknown): void {
    let message;
    let stackTrace:string="";

    if (error instanceof HttpErrorResponse) {
      // Server error
      message = this.errorService.getServerErrorMessage(error.error);
      stackTrace = this.errorService.getServerErrorStackTrace(error.error);
      this.notifier.showError(message);
    } else {
      // Client Error
      message = this.errorService.getClientErrorMessage(error);
      this.notifier.showError(message);
    }
    // Always log errors
    this.logger.logError(message);
    console.log(error);


    /**
     this.logger.error(error);
     const errorContext = {
      route: this.router.url,
      callerGivenContext: context,
      // TODO: Add any other context information here
    };

     if (environment.production && !isDebugMode()) {
      this.alert.error(`> ${this.translationService.translate('errors.generic')}`);
      // TODO: Consider sending the error details to an error tracking service here
    } else {
      this.logger.error(`> Error context:`, errorContext);
      this.alert.error(`> ${this.translationService.translate('errors.verboseGenericError')} ${error}`);
    }
     **/
  }
}
