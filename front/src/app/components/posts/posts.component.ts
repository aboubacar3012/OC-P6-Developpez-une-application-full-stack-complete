import {Component, OnInit} from '@angular/core';
import {map, Observable, Subscription} from "rxjs";
import {PostResponse} from "../../interfaces/PostResponse";
import {PostService} from "../../services/post.service";
import {UserService} from "../../services/user.service";
import {UserResponse} from "../../interfaces/UserResponse";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  public postResponse: PostResponse | undefined;
  public userResponse: UserResponse | undefined;

  constructor(private postService: PostService, private userService: UserService) {
  }

  ngOnInit(): void {
    this.getPosts()

  }

  getPosts(): void {
    // return this.postService.getPosts();
    this.postService
      .getPosts()
      .subscribe((postResponse: PostResponse) => {
        this.postResponse = postResponse;
      })
    this.userService
      .getUsers()
      .subscribe((userResponse: UserResponse) => {
        this.userResponse = userResponse;
      })
  }

  getUsernameById(id: number): string {
    if (this.userResponse) {
      const users = this.userResponse._embedded.userList;
      const author = users.find(user => user.id === id);
      if(author) return author.username;
    }
    return "Inconnu"
  }


  protected readonly Number = Number;
}
