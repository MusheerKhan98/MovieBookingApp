import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from 'src/app/movie.service';
import { AddTicketComponent } from './add-ticket.component';

describe('AddTicketComponent', () => {
  let component: AddTicketComponent;
  let fixture: ComponentFixture<AddTicketComponent>;

  beforeEach(() => {
    const formBuilderStub = () => ({ group: (object: any) => ({}) });
    const routerStub = () => ({});
    const activatedRouteStub = () => ({ snapshot: { params: {} } });
    const movieServiceStub = () => ({
      getAllSeats: (arg: any, arg2: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) }),
      addSeats: (value: any, moviePk: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) })
    });
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [AddTicketComponent],
      providers: [
        { provide: FormBuilder, useFactory: formBuilderStub },
        { provide: Router, useFactory: routerStub },
        { provide: ActivatedRoute, useFactory: activatedRouteStub },
        { provide: MovieService, useFactory: movieServiceStub }
      ]
    });
    fixture = TestBed.createComponent(AddTicketComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it(`showAlert has default value`, () => {
    expect(component.showAlert).toEqual(false);
  });

  it(`displayElement has default value`, () => {
    expect(component.displayElement).toEqual(false);
  });

  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      const formBuilderStub: FormBuilder = fixture.debugElement.injector.get(
        FormBuilder
      );
      const movieServiceStub: MovieService = fixture.debugElement.injector.get(
        MovieService
      );
      spyOn(formBuilderStub, 'group').and.callThrough();
      spyOn(movieServiceStub, 'getAllSeats').and.callThrough();
      component.ngOnInit();
      expect(formBuilderStub.group).toHaveBeenCalled();
      expect(movieServiceStub.getAllSeats).toHaveBeenCalled();
    });
  });
});
