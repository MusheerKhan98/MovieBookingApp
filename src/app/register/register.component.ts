import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  customerForm!: FormGroup;
  errorMessage!: string;
  showAlert = false;
  errorBool= false;
  constructor(
    private fb: FormBuilder,
    private lService: LoginService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.customerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: [''],
      loginId: ['', Validators.required],
      password: ['',[Validators.required,
        Validators.pattern('(?=\\D*\\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z]).{8,30}')]],
      email: [''],
      contactNumber: ['',  Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")],
    });
  }
  addCustomer() {
    this.lService.register(this.customerForm.value).subscribe(
      (res: any) => {
        this.showAlert=true;
      },
      (error: any) => {
        this.errorBool=true;
        this.errorMessage = error;
      }
    );
  }
}

