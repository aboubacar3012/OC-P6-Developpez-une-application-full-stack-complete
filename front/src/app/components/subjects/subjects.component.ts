import {Component, OnInit} from '@angular/core';
import {SubjectResponse} from "../../interfaces/subject";
import {SubjectService} from "../../services/subject.service";
import {SubscriptionService} from "../../services/subscription.service";

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.scss']
})
export class SubjectsComponent implements OnInit {
  public subjectResponse!: SubjectResponse;
  public subscriptions!:any;

  constructor(private subjectService: SubjectService, private subscriptionService: SubscriptionService) {
  }

  ngOnInit(): void {
    this.getSubjects();
    this.getSubscriptions();


  }

  getSubjects(): void {
    this.subjectService
      .getSubjects()
      .subscribe((subjectResponse: SubjectResponse) => this.subjectResponse = subjectResponse)
  }

  subscribe(userId: number, subjectId: number) {
    // console.log(userId, subjectId)
    return this.subscriptionService.subscribe(userId, subjectId).subscribe((response) => {
      console.log(response);
      this.getSubjects()
    });
  }

  unsubscribe(userId: number, subjectId: number) {
    return this.subscriptionService.unsubscribe(userId, subjectId).subscribe((response) => {
      console.log(response);
      this.getSubjects()
    });
  }

  alreadySubscribed(userId: number, subjectId: number) {
    return this.subscriptions.find((subscription: any) => subscription.userId === userId && subscription.subjectId === subjectId);
  }

  getSubscriptions(){
    this.subscriptionService.subscriptions().subscribe((subscriptions: any) => this.subscriptions = subscriptions);
  }
}
