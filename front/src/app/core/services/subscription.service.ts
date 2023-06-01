import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  private pathService = `/api/subscriptions`;
  constructor(private httpClient: HttpClient) {}

  public subscriptions(): Observable<any>{
    return this.httpClient.get<any>(`${this.pathService}`);
  }

  public subscribe(userId:number, subjectId:number): Observable<boolean>{
    return this.httpClient.post<boolean>(`${this.pathService}/subscribe?userId=${userId}&subjectId=${subjectId}`, {});
  }

  public unsubscribe(userId:number, subjectId:number): Observable<boolean>{
    return this.httpClient.post<boolean>(`${this.pathService}/unsubscribe?userId=${userId}&subjectId=${subjectId}`, {});
  }


}
