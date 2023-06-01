import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SubjectsComponent} from "./components/subjects/subjects.component";
import {SubjectComponent} from "./components/subject/subject.component";
import {SubjectsRoutingModule} from "./subjects-routing.module";


@NgModule({
  declarations: [
    SubjectsComponent,
    SubjectComponent
  ],
  imports: [
    CommonModule,
    SubjectsRoutingModule
  ],
  exports: [
    SubjectsComponent,
    SubjectComponent
  ]
})
export class SubjectsModule {
}
