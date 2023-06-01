import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "../core/guards/auth.guard";
import {SubjectsComponent} from "./components/subjects/subjects.component";

const routes: Routes = [
  {path: '', component: SubjectsComponent, canActivate: [AuthGuard]},
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})

export class SubjectsRoutingModule {
}
