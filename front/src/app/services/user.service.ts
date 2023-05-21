import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User, UserResponse} from "../interfaces/User";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private pathService = `/api/users`;
  constructor(private httpClient: HttpClient) {}

  public getUsers():Observable<UserResponse>{
    return this.httpClient.get<UserResponse>(this.pathService)
  }

  public getUserById(id:number): Observable<User>{
    return this.httpClient.get<User>(`${this.pathService}/${id}`);
  }

  public updateUserById(id:number, user: {username:string, email:string}): Observable<any>{
    return this.httpClient.put<User>(`${this.pathService}/${id}`, user);
  }
}
