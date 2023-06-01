import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserService} from "../services/user.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {


  constructor(private userService: UserService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (request.url === "/api/auth/registration" || request.url === "/api/auth/login" || !this.userService.getToken())
      return next.handle(request);

    if (request.url === "/api/auth/logout") {
      const modifiedRequest = request.clone({ headers: request.headers.delete("Authorization") });
      return next.handle(modifiedRequest);
    }

    const headers = new HttpHeaders()
      .append("Authorization", `Bearer ${this.userService.getToken()}`)
    const modifiedRequest = request.clone({headers});
    return next.handle(modifiedRequest);
  }
}
