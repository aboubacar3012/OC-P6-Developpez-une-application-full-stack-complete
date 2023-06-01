import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "../core/guards/auth.guard";
import {UserProfileComponent} from "./components/user-profile/user-profile.component";

const routes: Routes = [
  {path: '', component: UserProfileComponent, canActivate: [AuthGuard]},
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})

export class ProfileRoutingModule {
}
