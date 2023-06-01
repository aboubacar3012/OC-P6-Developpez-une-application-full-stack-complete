import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {AuthGuard} from "./core/guards/auth.guard";

const routes: Routes =
  [
    {path: '', component: HomeComponent, canActivate: [AuthGuard]},
    {
      path: 'posts',
      canActivate: [AuthGuard],
      loadChildren: () => import('./posts/posts.module').then(m => m.PostsModule)
    },
    {
      path: 'subjects',
      canActivate: [AuthGuard],
      loadChildren: () => import('./subjects/subjects.module').then(m => m.SubjectsModule)
    },
    {
      path: 'profile',
      canActivate: [AuthGuard],
      loadChildren: () => import('./profile/profile.module').then(m => m.ProfileModule)
    },
    // {path: "**", redirectTo: "posts"}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
