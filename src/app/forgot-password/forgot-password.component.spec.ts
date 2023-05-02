import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { FormsModule } from '@angular/forms';
import { ForgotPasswordComponent } from './forgot-password.component';

describe('ForgotPasswordComponent', () => {
  let component: ForgotPasswordComponent;
  let fixture: ComponentFixture<ForgotPasswordComponent>;

  beforeEach(() => {
    const routerStub = () => ({});
    const loginServiceStub = () => ({
      updatePassword: (loginId: any, newPassword: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) })
    });
    TestBed.configureTestingModule({
      imports: [FormsModule],
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [ForgotPasswordComponent],
      providers: [
        { provide: Router, useFactory: routerStub },
        { provide: LoginService, useFactory: loginServiceStub }
      ]
    });
    fixture = TestBed.createComponent(ForgotPasswordComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it(`showAlert has default value`, () => {
    expect(component.showAlert).toEqual(false);
  });

  it(`errorBool has default value`, () => {
    expect(component.errorBool).toEqual(false);
  });

  describe('updatePassword', () => {
    it('makes expected calls', () => {
      const loginServiceStub: LoginService = fixture.debugElement.injector.get(
        LoginService
      );
      spyOn(loginServiceStub, 'updatePassword').and.callThrough();
      component.updatePassword();
      expect(loginServiceStub.updatePassword).toHaveBeenCalled();
    });
  });
});
