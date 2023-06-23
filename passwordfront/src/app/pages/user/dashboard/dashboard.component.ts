import { LoginService } from './../../../service/login.service';
import { HttpErrorResponse } from '@angular/common/http';
import { PasswordService } from './../../../password.service';
import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from 'src/app/service/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {
  public password = {
    accountname: '',
    username: '',
    accPassword: '',
    userEmail: '',
  };

  public showPassword: boolean = false;

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  constructor(
    private passwordService: PasswordService,
    private snack: MatSnackBar,
    private route: ActivatedRoute,
    private login: LoginService
  ) {}
  user: any = this.login.getUser();
  email: any = this.user.email;

  ngOnInit() {}

  formSubmit() {
    this.passwordService.addPassword(this.password).subscribe({
      next: (response) => {
        Swal.fire('Sucess', 'Your Password is saved', 'success');
      },
      error: (error: HttpErrorResponse) => {
        let errorMessage = error.error.message;
        this.snack.open(`${errorMessage}`, '', {
          duration: 1000,
        });
      },
    });
  }
}
