import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../interfaces/User";
import {LoginRegistrationResponse} from "../interfaces/LoginRegistrationResponse";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private pathService = `/api/auth`;

  constructor(private httpClient: HttpClient) {
  }

  public registration(user: User): Observable<LoginRegistrationResponse> {
    return this.httpClient.post<LoginRegistrationResponse>(`${this.pathService}/registration`, user);
  }

  public login(request: { email: string, password: string }): Observable<LoginRegistrationResponse> {
    return this.httpClient.post<LoginRegistrationResponse>(`${this.pathService}/login`, request);
  }

  public logout(): Observable<any> {
    return this.httpClient.post<any>(`${this.pathService}/logout`, {});
  }


}
