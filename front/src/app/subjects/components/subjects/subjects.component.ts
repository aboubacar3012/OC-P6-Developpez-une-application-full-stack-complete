import {Component, OnInit} from '@angular/core';
import {SubjectResponse} from "../../../core/interfaces/subject";
import {SubjectService} from "../../../core/services/subject.service";
import {SubscriptionService} from "../../../core/services/subscription.service";
import {User} from "../../../core/interfaces/User";
import {UserService} from "../../../core/services/user.service";

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.scss']
})
export class SubjectsComponent implements OnInit {
  public subjectResponse!: SubjectResponse;
  public currentUser!: User;
  public subscriptions!: any;

  constructor(private subjectService: SubjectService, private subscriptionService: SubscriptionService, private userService: UserService) {
  }

  ngOnInit(): void {
    this.getSubjects();
    this.getSubscriptions();
    this.userService.getCurrentUser().subscribe((user) => this.currentUser = user);

  }

  getSubjects(): void {
    this.subjectService
      .getSubjects()
      .subscribe((subjectResponse: SubjectResponse) => this.subjectResponse = subjectResponse)
  }

  subscribe(subjectId: number) {
    if (this.currentUser && this.currentUser.id) {
      // console.log(userId, subjectId)
      return this.subscriptionService.subscribe(this.currentUser.id, subjectId).subscribe((response) => {
         this.getSubjects()
        window.location.reload()
      });
    } else return;
  }

  unsubscribe(subjectId: number) {
    if (this.currentUser && this.currentUser.id) {
      return this.subscriptionService.unsubscribe(this.currentUser.id, subjectId).subscribe((response) => {
        this.getSubjects()
        window.location.reload()
      });
    } else return;
  }

  alreadySubscribed(subjectId: number) {
    if (this.currentUser && this.currentUser.id) {
      return this.subscriptions.find((subscription: any) => subscription.userId === this.currentUser.id && subscription.subjectId === subjectId);
    }else return;
  }

  getSubscriptions() {
    this.subscriptionService.subscriptions().subscribe((subscriptions: any) => this.subscriptions = subscriptions);
  }
}
