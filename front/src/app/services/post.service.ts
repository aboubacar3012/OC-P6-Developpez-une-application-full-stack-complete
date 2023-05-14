import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {PostResponse} from "../interfaces/PostResponse";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private pathService = `/api/posts`;
  constructor(private httpClient: HttpClient) {}

  public getPosts():Observable<PostResponse>{
    return this.httpClient.get<PostResponse>(this.pathService)
  }
}
