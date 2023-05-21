import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../interfaces/Comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private pathService = `/api/comments`;
  constructor(private httpClient: HttpClient) {}

  public comments(postId: number | undefined): Observable<any>{
    return this.httpClient.get<any>(`${this.pathService}/${postId}`);
  }

  public addComment(comment: Comment):Observable<Comment>{
    return this.httpClient.post<Comment>(`${this.pathService}`, comment);
  }
}
