import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login.service';
import { MovieService } from 'src/app/movie.service';
import { ViewMovieDetailComponent } from './view-movie-detail.component';

describe('ViewMovieDetailComponent', () => {
  let component: ViewMovieDetailComponent;
  let fixture: ComponentFixture<ViewMovieDetailComponent>;

  beforeEach(() => {
    const activatedRouteStub = () => ({ snapshot: { params: {} } });
    const routerStub = () => ({});
    const loginServiceStub = () => ({});
    const movieServiceStub = () => ({
      getMovieById: (movieName: any, theatreName: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) })
    });
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [ViewMovieDetailComponent],
      providers: [
        { provide: ActivatedRoute, useFactory: activatedRouteStub },
        { provide: Router, useFactory: routerStub },
        { provide: LoginService, useFactory: loginServiceStub },
        { provide: MovieService, useFactory: movieServiceStub }
      ]
    });
    fixture = TestBed.createComponent(ViewMovieDetailComponent);
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
      spyOn(movieServiceStub, 'getMovieById').and.callThrough();
      component.ngOnInit();
      expect(movieServiceStub.getMovieById).toHaveBeenCalled();
    });
  });
});
