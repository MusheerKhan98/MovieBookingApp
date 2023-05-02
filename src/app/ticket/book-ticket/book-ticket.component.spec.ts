import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from 'src/app/movie.service';
import { BookTicketComponent } from './book-ticket.component';

describe('BookTicketComponent', () => {
  let component: BookTicketComponent;
  let fixture: ComponentFixture<BookTicketComponent>;

  beforeEach(() => {
    const formBuilderStub = () => ({ group: (object: any) => ({}) });
    const routerStub = () => ({ navigate: (array: any) => ({}) });
    const activatedRouteStub = () => ({
      params: {
        subscribe: (f: (arg0: {}) => any) => f({}),
        pipe: () => ({ subscribe: (f: (arg0: {}) => any) => f({}) })
      },
      snapshot: { params: {} }
    });
    const movieServiceStub = () => ({
      getAllSeats: (arg: any, arg2: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) }),
      addTicket: (arg: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) }),
      setBookingId: (ticketId: any) => ({})
    });
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [BookTicketComponent],
      providers: [
        { provide: FormBuilder, useFactory: formBuilderStub },
        { provide: Router, useFactory: routerStub },
        { provide: ActivatedRoute, useFactory: activatedRouteStub },
        { provide: MovieService, useFactory: movieServiceStub }
      ]
    });
    fixture = TestBed.createComponent(BookTicketComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it(`isReadOnly has default value`, () => {
    expect(component.isReadOnly).toEqual(true);
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
