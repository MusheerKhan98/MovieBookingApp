import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Movie } from 'src/app/movie';
import { MovieService } from 'src/app/movie.service';
import { AddMovieComponent } from './add-movie.component';

describe('AddMovieComponent', () => {
  let component: AddMovieComponent;
  let fixture: ComponentFixture<AddMovieComponent>;

  beforeEach(() => {
    const formBuilderStub = () => ({ group: (object: any) => ({}) });
    const domSanitizerStub = () => ({ bypassSecurityTrustUrl: (arg: any) => ({}) });
    const routerStub = () => ({ navigate: (array: any) => ({}) });
    const movieServiceStub = () => ({
      addMovie: (preparedFormData: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) })
    });
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [AddMovieComponent],
      providers: [
        { provide: FormBuilder, useFactory: formBuilderStub },
        { provide: DomSanitizer, useFactory: domSanitizerStub },
        { provide: Router, useFactory: routerStub },
        { provide: MovieService, useFactory: movieServiceStub }
      ]
    });
    fixture = TestBed.createComponent(AddMovieComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it(`errorBool has default value`, () => {
    expect(component.errorBool).toEqual(false);
  });

  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      const formBuilderStub: FormBuilder = fixture.debugElement.injector.get(
        FormBuilder
      );
      spyOn(formBuilderStub, 'group').and.callThrough();
      component.ngOnInit();
      expect(formBuilderStub.group).toHaveBeenCalled();
    });
  });

});
