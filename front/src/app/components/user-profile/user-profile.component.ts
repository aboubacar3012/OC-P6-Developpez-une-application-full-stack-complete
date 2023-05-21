import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {User, UserResponse} from "../../interfaces/User";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  public updateUserForm!: FormGroup;
  public user!:any;

  public username:string = "";
  public email:string =  "";

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUserById(1);
  }

  onSubmitForm(){
    this.userService.updateUserById(1,{username:this.username, email:this.email}).subscribe()
  }

  getUserById(id:number){
    return this.userService.getUserById(id).subscribe((user:User) => {
      this.user= user;
      this.username = user.username;
      this.email = user.email;
    });
  }

}
