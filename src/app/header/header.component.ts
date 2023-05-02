import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  userRole: any;
  //status: boolean;
  constructor(public lService: LoginService,
    private router: Router) {}

  Logout() {
    this.lService.logout();
    this.router.navigate(['/movie']);
  }
}

