import {AbstractControl, ValidatorFn} from '@angular/forms';

export function passwordValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const password = control.value;
    const hasNumber = /[0-9]/.test(password);
    const hasLowercase = /[a-z]/.test(password);
    const hasUppercase = /[A-Z]/.test(password);
    const hasSpecialChar = /[!@#$%^&*]/.test(password);

    const valid = password && password.length >= 8 && hasNumber && hasLowercase && hasUppercase && hasSpecialChar;

    return valid ? null : {invalidPassword: true};
  };
}
