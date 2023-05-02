import { TestBed } from '@angular/core/testing';
import { HttpHandler } from '@angular/common/http';
import { HttpRequest } from '@angular/common/http';
import { BasicAuthHttpInterceptorService } from './basic-auth-http-interceptor.service';

describe('BasicAuthHttpInterceptorService', () => {
  let service: BasicAuthHttpInterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BasicAuthHttpInterceptorService]
    });
    service = TestBed.inject(BasicAuthHttpInterceptorService);
  });

  it('can load instance', () => {
    expect(service).toBeTruthy();
  });

});
