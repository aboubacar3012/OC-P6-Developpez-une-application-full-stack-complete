import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss']
})
export class PostFormComponent implements OnInit {
  postForm!: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.postForm = this.formBuilder.group({
      title: [null],
      content: [null],
      subject: [null]
    })
  }

  onSubmitForm(){
    console.log(this.postForm.value);
  }

}
