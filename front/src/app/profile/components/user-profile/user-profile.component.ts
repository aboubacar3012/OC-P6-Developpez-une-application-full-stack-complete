import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../core/services/user.service";
import {User} from "../../../core/interfaces/User";
import {AuthService} from "../../../core/services/auth.service";
import {Router} from "@angular/router";
import {SubjectResponse} from "../../../core/interfaces/subject";
import {SubjectService} from "../../../core/services/subject.service";
import {SubscriptionService} from "../../../core/services/subscription.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  public currentUser!: User;
  public subjectResponse!: SubjectResponse;
  public subscriptions!: any;

  public firstName: string = "";
  public lastName: string = "";
  public email: string = "";

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private subjectService: SubjectService,
    private subscriptionService: SubscriptionService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.getSubjects();
    this.getSubscriptions();
    this.userService.getCurrentUser().subscribe(user => {
      this.currentUser = user;
      this.firstName = user.firstName;
      this.lastName = user.lastName;
      this.email = user.email;
    });
  }

  onSubmitForm() {
    if (this.currentUser && this.currentUser.id) {
      this.userService.updateUserById(this.currentUser.id, {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email
      }).subscribe()
    }
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
    } else return;
  }

  getSubjectsSubscribed() {
    if (this.currentUser && this.currentUser.id) {
      return this.subjectResponse?._embedded?.subjectList.filter((subject: any) => this.subscriptions.find((subscription: any) => subscription.userId === this.currentUser.id && subscription.subjectId === subject.id));
    } else return;
  }

  getSubscriptions() {
    this.subscriptionService.subscriptions().subscribe((subscriptions: any) => this.subscriptions = subscriptions);
  }

  onLogout() {
    this.authService.logout().subscribe();
    localStorage.removeItem("token");
    this.router.navigateByUrl("/login").then(r => null);
  }
}
