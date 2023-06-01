import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PostsComponent} from "./components/posts/posts.component";
import {PostFormComponent} from "./components/post-form/post-form.component";
import {PostComponent} from "./components/post/post.component";
import {PostsRoutingModule} from "./posts-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    PostsComponent,
    PostFormComponent,
    PostComponent
  ],
  imports: [
    CommonModule,
    PostsRoutingModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  exports: [
    PostsComponent,
    PostFormComponent,
    PostComponent
  ]
})
export class PostsModule {
}
