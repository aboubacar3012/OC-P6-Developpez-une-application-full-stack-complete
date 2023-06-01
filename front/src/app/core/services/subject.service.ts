import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubjectResponse} from "../interfaces/subject";

@Injectable({
  providedIn: 'root'
})
export class SubjectService {
  private pathService = `/api/subjects`;
  constructor(private httpClient: HttpClient) {}

  public getSubjects():Observable<SubjectResponse>{
    return this.httpClient.get<SubjectResponse>(this.pathService)
  }
}
