import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SubjectService} from "../../services/subject.service";
import {Subject} from "../../interfaces/subject";
import {map} from "rxjs";
import {PostService} from "../../services/post.service";

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss']
})
export class PostFormComponent implements OnInit {
  public postForm!: FormGroup;
  public subjects!: Subject[];

  constructor(private formBuilder: FormBuilder, private subjectService: SubjectService, private postService: PostService) {
  }

  ngOnInit(): void {
    this.getSubjects();
    this.postForm = this.formBuilder.group({
      title: [null, Validators.required],
      content: [null, Validators.required],
      subject: [null, Validators.required]
    })
  }

  onSubmitForm() {
    const {title, content, subject} = this.postForm.value;
    const post = {title: title, content: content, subjectId: Number(subject), authorId: 1}
    this.postService.addPost(post).subscribe((_) => {
      this.postForm = this.formBuilder.group({
        title: [null, Validators.required],
        content: [null, Validators.required],
        subject: [null, Validators.required]
      })
    });
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
