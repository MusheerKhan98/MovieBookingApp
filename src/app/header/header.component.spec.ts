import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { HeaderComponent } from './header.component';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

  beforeEach(() => {
    const routerStub = () => ({ navigate: (array: any) => ({}) });
    const loginServiceStub = () => ({ logout: () => ({}) });
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [HeaderComponent],
      providers: [
        { provide: Router, useFactory: routerStub },
        { provide: LoginService, useFactory: loginServiceStub }
      ]
    });
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  describe('Logout', () => {
    it('makes expected calls', () => {
      const routerStub: Router = fixture.debugElement.injector.get(Router);
      const loginServiceStub: LoginService = fixture.debugElement.injector.get(
        LoginService
      );
      spyOn(routerStub, 'navigate').and.callThrough();
      spyOn(loginServiceStub, 'logout').and.callThrough();
      component.Logout();
      expect(routerStub.navigate).toHaveBeenCalled();
      expect(loginServiceStub.logout).toHaveBeenCalled();
    });
  });
});
