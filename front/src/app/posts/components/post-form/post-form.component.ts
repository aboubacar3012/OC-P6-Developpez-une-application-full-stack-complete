import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SubjectService} from "../../../core/services/subject.service";
import {Subject} from "../../../core/interfaces/subject";
import {map} from "rxjs";
import {PostService} from "../../../core/services/post.service";
import {UserService} from "../../../core/services/user.service";
import {User} from "../../../core/interfaces/User";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss']
})
export class PostFormComponent implements OnInit {
  public postForm!: FormGroup;
  public subjects!: Subject[];
  public currentUser!: User;

  constructor(
    private formBuilder: FormBuilder,
    private subjectService: SubjectService,
    private postService: PostService,
    private userService: UserService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.getSubjects();
    this.userService.getCurrentUser().subscribe((user) => this.currentUser = user);
    this.postForm = this.formBuilder.group({
      title: [null, Validators.required],
      content: [null, Validators.required],
      subject: [null, Validators.required]
    })
  }

  onSubmitForm() {
    if (this.currentUser && this.currentUser.id) {
      const {title, content, subject} = this.postForm.value;
      const post = {title: title, content: content, subjectId: Number(subject), authorId: this.currentUser.id}
      this.postService.addPost(post).subscribe((_) => {
        this.postForm = this.formBuilder.group({
          title: [null, Validators.required],
          content: [null, Validators.required],
          subject: [null, Validators.required]
        })
        this.router.navigateByUrl("/posts")
      });
    }
  }

  getSubjects(): void {
    this.subjectService
      .getSubjects()
      .pipe(
        map((subjectResponse) => subjectResponse._embedded.subjectList),
      )
      .subscribe((subjects: Subject[]) => {
        this.subjects = subjects;
      })
  }

}
