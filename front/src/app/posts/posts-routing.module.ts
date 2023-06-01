import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {PostsComponent} from "./components/posts/posts.component";
import {AuthGuard} from "../core/guards/auth.guard";
import {PostComponent} from "./components/post/post.component";
import {PostFormComponent} from "./components/post-form/post-form.component";

const routes: Routes = [
  {path: '', component: PostsComponent, canActivate: [AuthGuard]},
  {path: 'post-form', component: PostFormComponent, canActivate: [AuthGuard]},
  {path: ':id', component: PostComponent, canActivate: [AuthGuard]},
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})

export class PostsRoutingModule {
}
