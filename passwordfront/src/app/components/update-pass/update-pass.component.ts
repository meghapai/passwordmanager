import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { PasswordService } from 'src/app/password.service';
import { Component } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-pass',
  templateUrl: './update-pass.component.html',
  styleUrls: ['./update-pass.component.css'],
})
export class UpdatePassComponent {
  id: any;
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
    private route: ActivatedRoute,
    private router: Router,
    private snack: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.passwordService.getPasswordById(this.id).subscribe({
      next: (response: any) => {
        //console.log(response);
        this.password = response;
      },
      error: (error: HttpErrorResponse) => {},
    });
  }

  formSubmit() {
    this.passwordService.updatePassword(this.id, this.password).subscribe({
      next: (response: any) => {
        Swal.fire('Sucess', 'Password is updated', 'success');
        this.router.navigate(['/passdata']);
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
