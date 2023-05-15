import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PostService} from "../../services/post.service";
import {Post} from "../../interfaces/Post";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {
  public post!: Post;

  constructor(private route: ActivatedRoute, private postService: PostService) { }

  ngOnInit(): void {
    const postId = +this.route.snapshot.params['id'];
    this.postService
      .getPostById(postId)
      .subscribe((post: Post) => {
        this.post = post;
        console.log(post)
      })
  }

}
