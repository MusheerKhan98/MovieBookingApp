import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
 export class ForgotPasswordComponent {
  loginId = ''
  newPassword = ''
  c_newPassword = ''

  showAlert=false;
  errorBool=false;

  constructor(private service: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  updatePassword() {
      this.service.updatePassword(this.loginId,this.newPassword).subscribe(
        data => this.showAlert=true,
        (error: any) => {
          this.errorBool = true;
        }
    )

  }

}
