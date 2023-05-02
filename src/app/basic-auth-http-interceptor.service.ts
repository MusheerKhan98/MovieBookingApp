import { HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthHttpInterceptorService implements HttpInterceptor {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let headers: HttpHeaders = new HttpHeaders();
    if(req.headers.get("skip")){
      return next.handle(req);
    }
    
    else if (sessionStorage.getItem('username') && sessionStorage.getItem('basicauth')) {
      console.log("in here");
      headers = req.headers.set('Authorization',Object.values(sessionStorage)[0])
      return next.handle(req.clone({headers}));
    }  
    else{
      return next.handle(req);
    }
  }
}