import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../core/services/auth.service";
import {LoginRegistrationResponse} from "../../../core/interfaces/LoginRegistrationResponse";
import {Router} from "@angular/router";
import {UserService} from "../../../core/services/user.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  public registrationForm!: FormGroup;
  private passwordPattern = /^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#$%^&()-_=+{};:,<.>]).$/

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router, private userService: UserService) {
  }

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      firstName: [null, Validators.required],
      lastName: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required, Validators.min(8)]] // Validators.pattern(this.passwordPattern)
    })
  }

  onSubmitForm() {
    if (this.registrationForm.invalid) return;
    console.log(this.registrationForm.value);
    this.authService.registration(this.registrationForm.value).subscribe((response: LoginRegistrationResponse) => {
      this.userService.addUserAuth(response.token, response.email)
      this.registrationForm = this.formBuilder.group({
        username: [null, Validators.required],
        email: [null, [Validators.required, Validators.email]],
        password: [null, [Validators.required, Validators.min(8)]] // Validators.pattern(this.passwordPattern)
      })
      this.router.navigateByUrl("/posts").then(r => null)
    })
  }

}
