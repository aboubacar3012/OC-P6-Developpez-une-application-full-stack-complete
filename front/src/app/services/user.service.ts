import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserResponse} from "../interfaces/UserResponse";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private pathService = `/api/users`;
  constructor(private httpClient: HttpClient) {}

  public getUsers():Observable<UserResponse>{
    return this.httpClient.get<UserResponse>(this.pathService)
  }
}
