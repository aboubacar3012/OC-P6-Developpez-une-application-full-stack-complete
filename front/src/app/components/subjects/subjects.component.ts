import {Component, OnInit} from '@angular/core';
import {SubjectResponse} from "../../interfaces/subject";
import {SubjectService} from "../../services/subject.service";

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.scss']
})
export class SubjectsComponent implements OnInit {
  public subjectResponse!: SubjectResponse;

  constructor(private subjectService: SubjectService) {
  }

  ngOnInit(): void {
    this.getSubjects();
  }

  getSubjects(): void {
    this.subjectService
      .getSubjects()
      .subscribe((subjectResponse: SubjectResponse) => this.subjectResponse = subjectResponse)
  }

}
