import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from '../login-request';
import { LoginService } from '../login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit  {
  loginForm!: FormGroup;
  errorMessage!: string;
  errorBool=false;
  //private userRole: string;
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private lService : LoginService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }
  doLogin() {
    this.lService
      .login(this.loginForm.value.username, this.loginForm.value.password)
      .subscribe(
        (res: any) => {
          if (res === 'admin') {
            this.lService.setUserRole(true);
            console.log(this.lService.getUserRole()+"1111111111");
          } else {
            this.lService.setUserRole(false);
          }
          console.log(res);
          console.log(res);
          this.lService.setLoginStatus(true);
         // this.lService.setLoginStatus(res.loginStatus);

          //this.lService.setUserRole(res.user.role);
          //this.userRole = res.user.role;
          this.router.navigate(['']);
        },
        (error: any) => {
          console.log(error)
          this.errorMessage = error;
          this.errorBool=true;
        }
      );
  }
}
