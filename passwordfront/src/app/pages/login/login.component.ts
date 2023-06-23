import { HttpErrorResponse } from '@angular/common/http';
import { LoginService } from './../../service/login.service';
import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginData = {
    email: '',
    password: '',
  };

  constructor(
    private login: LoginService,
    private snack: MatSnackBar,
    private router: Router
  ) {}
  public showPassword: boolean = false;

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  formSubmit() {
    //request server to generate token

    this.login.generateToken(this.loginData).subscribe({
      next: (response: any) => {
        //login..
        this.login.loginUser(response.token);

        this.login.getCurrentUser().subscribe({
          next: (user: any) => {
            this.login.setUser(user);
            console.log(user);
            //redirect
            if (this.login.getUser()) {
              // window.location.href = '/dashboard';
              this.router.navigate(['dashboard']);
              this.login.loginStatusSubject.next(true);
            } else {
              this.login.logout();
            }
          },
          error: (error: HttpErrorResponse) => {
            let errorMessage = error.error.message;
            this.snack.open(`${errorMessage}`, '', { duration: 1000 });
          },
        });
      },
      error: (error: HttpErrorResponse) => {
        let errorMessage = error.error.message;
        this.snack.open(`${errorMessage}`, '', { duration: 1000 });
      },
    });
  }
}
