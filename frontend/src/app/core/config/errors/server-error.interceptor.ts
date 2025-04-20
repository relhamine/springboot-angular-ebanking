import {inject, Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {GlobalErrorHandler} from "./global-error-handler";

@Injectable()
export class ServerErrorInterceptor implements HttpInterceptor {
  private errorHandler = inject(GlobalErrorHandler);

  constructor() {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = 'Une erreur est survenue. Veuillez réessayer.';

        switch (error.status) {
          case 401:
            errorMessage = 'Vous n\'êtes pas autorisé à accéder à cette ressource.';
            break;
          case 403:
            errorMessage = 'Accès interdit.';
            break;
          case 404:
            errorMessage = 'La ressource demandée est introuvable.';
            break;
          case 500:
            errorMessage = 'Erreur interne du serveur. Veuillez réessayer plus tard.';
            break;
          default:
            break;
        }

        console.log("Erreur de la requête :", error);
        /**
         if (environment.production && !isDebugMode()) {
          this.alertService.error(errorMessage);
        }**/

        this.errorHandler.handleError(error);

        return throwError(() => error.error[Object.keys(error.error)[3]]);
      })
    );

    return next.handle(request);
  }
}
