import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User, UserResponse} from "../interfaces/User";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private pathService = `/api/users`;
  constructor(private httpClient: HttpClient,) {}

  public getUsers():Observable<UserResponse>{
    return this.httpClient.get<UserResponse>(this.pathService)
  }

  public getUserById(id:number): Observable<User>{
    return this.httpClient.get<User>(`${this.pathService}/${id}`);
  }

  public getUserByEmail(email:number): Observable<User>{
    return this.httpClient.get<User>(`${this.pathService}/get?email=${email}`);
  }

  public updateUserById(id:number, user: {firstName:string, lastName:string, email:string}): Observable<any>{
    return this.httpClient.put<User>(`${this.pathService}/${id}`, user);
  }

  public addUserAuth(token: string, email:string): void {
    localStorage.setItem("token", token);
    localStorage.setItem("email", email);
  }

  public getToken(): string | null {
    return localStorage.getItem("token");
  }
  public getEmail(): string | null {
    return localStorage.getItem("email");
  }

  public getCurrentUser():Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/get?email=${this.getEmail()}`)
  }
}
