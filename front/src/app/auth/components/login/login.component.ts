import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoginRegistrationResponse} from "../../../core/interfaces/LoginRegistrationResponse";
import {AuthService} from "../../../core/services/auth.service";
import {Router} from "@angular/router";
import {UserService} from "../../../core/services/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public loginForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router, private userService: UserService) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [null, Validators.required],
      password: [null, Validators.required]
    })
  }

  onSubmitForm() {
    const {email, password} = this.loginForm.value;
    this.authService.login({email, password}).subscribe((response: LoginRegistrationResponse) => {
      this.userService.addUserAuth(response.token, response.email)
      this.router.navigateByUrl("/posts").then(r => null)
    })
  }

}
