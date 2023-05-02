import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { MovieService } from 'src/app/movie.service';
import { DetailViewTicketComponent } from './detail-view-ticket.component';

describe('DetailViewTicketComponent', () => {
  let component: DetailViewTicketComponent;
  let fixture: ComponentFixture<DetailViewTicketComponent>;

  beforeEach(() => {
    const activatedRouteStub = () => ({});
    const routerStub = () => ({});
    const movieServiceStub = () => ({
      getBookingId: () => ({}),
      getBookingById: (bookingId: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) })
    });
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [DetailViewTicketComponent],
      providers: [
        { provide: ActivatedRoute, useFactory: activatedRouteStub },
        { provide: Router, useFactory: routerStub },
        { provide: MovieService, useFactory: movieServiceStub }
      ]
    });
    fixture = TestBed.createComponent(DetailViewTicketComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      const movieServiceStub: MovieService = fixture.debugElement.injector.get(
        MovieService
      );
      spyOn(movieServiceStub, 'getBookingId').and.callThrough();
      spyOn(movieServiceStub, 'getBookingById').and.callThrough();
      component.ngOnInit();
      expect(movieServiceStub.getBookingId).toHaveBeenCalled();
      expect(movieServiceStub.getBookingById).toHaveBeenCalled();
    });
  });
});
