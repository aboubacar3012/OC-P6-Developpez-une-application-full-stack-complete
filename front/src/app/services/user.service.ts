import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User, UserResponse} from "../interfaces/User";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private pathService = `/api/auth`;
  constructor(private httpClient: HttpClient) {}

  public getUsers():Observable<UserResponse>{
    return this.httpClient.get<UserResponse>(this.pathService)
  }
  public addUser(user: User): any {
    console.log(user)
    return this.httpClient.post<any>(`${this.pathService}/registration`,user);
  }
}
