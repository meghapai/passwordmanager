import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { PasswordService } from 'src/app/password.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-passdata',
  templateUrl: './passdata.component.html',
  styleUrls: ['./passdata.component.css'],
})
export class PassdataComponent {
  displayedColumns: string[] = [
    'Password ID',
    'Account Name',
    'User Name',
    'Account Password',
    'Update Password',
    'Delete Password',
  ];

  passwordDetails: any = [];

  constructor(
    private passwordService: PasswordService,
    private login: LoginService,
    private router: Router
  ) {
    this.getPassword();
  }

  ngOninit(): void {
    this.userData = this.login.getUser();
  }

  user: any = this.login.getUser();
  email: any = this.user.email;

  userData: any;

  getPassword() {
    console.log(this.email);
    this.passwordService.getPasswordUnique(this.email).subscribe({
      next: (response: any) => {
        console.log(response);
        this.passwordDetails = response;
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
      },
    });

    // this.passwordService.getPassword().subscribe({
    //   next: (response: any) => {
    //     console.log(response);
    //   },
    //   error: (error: HttpErrorResponse) => {},
    // });
  }

  deletePassword(passwordId: any) {
    if (confirm('Are you sure you want to delete this'))
      this.passwordService.deletePassword(passwordId).subscribe({
        next: (response: any) => {
          this.getPassword();
        },
        error: (error: HttpErrorResponse) => {},
      });
  }

  editPassword(passwordId: any) {
    this.router.navigate(['/updatePassword', passwordId]);
    // this.passwordService.getPasswordById(passwordId).subscribe({
    //   next: (response: any) => {},
    //   error: (error: HttpErrorResponse) => {},
    // });
  }
}
