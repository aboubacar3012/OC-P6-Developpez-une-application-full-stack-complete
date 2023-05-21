import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post, PostPayload, PostResponse} from "../interfaces/Post";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private pathService = `/api/posts`;
  constructor(private httpClient: HttpClient) {}

  public getPosts():Observable<PostResponse>{
    return this.httpClient.get<PostResponse>(this.pathService)
  }

  public getPostById(id: number): Observable<Post>{
    return this.httpClient.get<Post>(`${this.pathService}/${id}`)
  }

  public addPost(post: PostPayload): Observable<Post> {
    return this.httpClient.post<Post>(this.pathService, post);
  }
}
