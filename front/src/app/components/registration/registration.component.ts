import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  public registrationForm!: FormGroup;
  private passwordPattern = /^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#$%^&()-_=+{};:,<.>]).$/
  constructor(private formBuilder: FormBuilder, private authService: AuthService) { }

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      username: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required, Validators.min(8)]] // Validators.pattern(this.passwordPattern)
    })
  }

  onSubmitForm(){
    if(this.registrationForm.invalid) return;
    console.log(this.registrationForm.value);
    this.authService.addUser(this.registrationForm.value).subscribe(() => {
      this.registrationForm = this.formBuilder.group({
        username: [null, Validators.required],
        email: [null, [Validators.required, Validators.email]],
        password: [null, [Validators.required, Validators.min(8)]] // Validators.pattern(this.passwordPattern)
      })
    })
  }

}
