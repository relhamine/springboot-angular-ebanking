import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from 'environment/environment';
import {Credentials} from "../interfaces/credentials";
import {Token} from "../interfaces/token";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url = environment.backendHost+"/auth/signin"

  constructor(private http: HttpClient) { }

  login(credentials : Credentials): Observable<Token>{
    return this.http.post<Token>(this.url,credentials);
  }
}
