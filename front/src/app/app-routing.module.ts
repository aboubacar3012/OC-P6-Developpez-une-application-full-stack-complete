import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {RegistrationComponent} from "./components/registration/registration.component";
import {LoginComponent} from "./components/login/login.component";
import {PostsComponent} from "./components/posts/posts.component";
import {SubjectsComponent} from "./components/subjects/subjects.component";
import {PostComponent} from "./components/post/post.component";
import {PostFormComponent} from "./components/post-form/post-form.component";
import {UserProfileComponent} from "./components/user-profile/user-profile.component";

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes =
  [
    { path: '', component: HomeComponent},
    { path: 'registration', component: RegistrationComponent },
    { path: 'login', component: LoginComponent },
    { path: 'posts', component: PostsComponent },
    { path: 'posts/:id', component: PostComponent },
    { path: 'subjects', component: SubjectsComponent },
    { path: 'post-form', component: PostFormComponent },
    { path: 'profile', component: UserProfileComponent },
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
