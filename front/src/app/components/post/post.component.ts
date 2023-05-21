import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PostService} from "../../services/post.service";
import {Post} from "../../interfaces/Post";
import {CommentService} from "../../services/comment.service";
import {Comment} from "../../interfaces/Comment";
import {UserService} from "../../services/user.service";
import {User, UserResponse} from "../../interfaces/User";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {
  public post!: Post;
  public comments!:Comment[];
  public users!:UserResponse
  public commentForm!: FormGroup;
  public postId = +this.route.snapshot.params['id'];


  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService,
    private userService: UserService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.postService
      .getPostById(this.postId)
      .subscribe((post: Post) => {
        this.post = post;
        // console.log(post)
      })
    this.getComments(this.postId)
    this.getUsers()
    this.commentForm = this.formBuilder.group({
      content: [null, Validators.required],
    })
  }

  getComments(postId: number){
     this.commentService.comments(postId).subscribe((comments: Comment[]) => this.comments = comments);
  }

  getUsers(){
    this.userService.getUsers().subscribe((users) => this.users = users);
  }
  getAuthorNameByAuthorId(authorId:number){
    return this.users._embedded.userList.find(user => user.id = authorId)?.username
  }

  onSubmitForm() {
    const {content} = this.commentForm.value;
    const comment = {authorId:2, postId: this.post.id ?? 1, content: content}
    this.commentService.addComment(comment).subscribe((_) => {
      this.getComments(this.postId)
      this.commentForm = this.formBuilder.group({
        content: [null, Validators.required],
      })
    });
  }

}
