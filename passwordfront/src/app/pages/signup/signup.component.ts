import { UserService } from './../../service/user.service';
import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  constructor(private userService: UserService, private snack: MatSnackBar) {}

  public showPassword: boolean = false;

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  public user = {
    firstName: '',
    lastName: '',
    email: '',
    password: '',
  };

  formSubmit() {
    this.userService.addUser(this.user).subscribe({
      next: (response) => {
        Swal.fire('Sucess', 'Registration is successful', 'success');
      },
      error: (error: HttpErrorResponse) => {
        //alert('Something went wrong');
        let errorMessage = error.error.message;
        this.snack.open(`${errorMessage}`, '', {
          duration: 1000,
        });
      },
    });
  }
}
