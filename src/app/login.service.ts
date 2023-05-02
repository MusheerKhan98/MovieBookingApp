import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError, map, skip } from 'rxjs';
import { Login } from './login';
import { LoginRequest } from './login-request';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private url = 'http://localhost:8085/api/v1.0/moviebooking';
  httpOptions1 = {
    headers:
     new HttpHeaders({
      'Content-Type': 'application/json',
      'skip' : 'true'
    }),
  };
  httpOptions = {
    headers:
     new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  public isLoginStatus!: boolean;
  public userRole!: boolean;
  public customerId!: number;
  public loginRequest: LoginRequest = new LoginRequest;

  getLoginStatus() {
    return sessionStorage.getItem('username') && sessionStorage.getItem('basicauth')?true:false
  }

  setLoginStatus(status: any) {
    this.isLoginStatus = status;
  }

  getUserRole() {
    //return this.userRole;
   return  sessionStorage.getItem('username') === 'musheer'
  }

  setUserRole(role: any) {
    this.userRole = role;
  }

  constructor(private httpClient: HttpClient) {}

  register(cust: any): Observable<any> {
    return this.httpClient
      .post<any>(
        this.url + '/register',
        JSON.stringify(cust),
       this.httpOptions1
      )
      .pipe(catchError(this.handleError));
  }

  login(username: any, password: any): Observable<any> {
    console.log(username + password);
    //const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) })
   // .append('skip','true')
    this.loginRequest.username=username;
    this.loginRequest.password=password;
    return this.httpClient
      .post<string>(
        this.url + '/login',
        this.loginRequest,
      //  {headers, responseType:'text' as 'json'}
      {headers:
        {skip:"true"},
        responseType: 'text' as 'json',
      }
      )
      .pipe(
        map(
          userData => {
            sessionStorage.setItem('username', username);
            let authString = 'Basic ' + btoa(username + ':' + password);
            console.log(authString);
            sessionStorage.setItem('basicauth', authString);
            console.log(userData);
            return userData;
          }
        ),
        catchError(this.handleError))
      ;
  }

  logout() {
    this.isLoginStatus = false;
    this.userRole = false;
    sessionStorage.removeItem('username')
    sessionStorage.removeItem('basicauth');
  }

  updatePassword(loginId: String, password: string) {
    return this.httpClient.put(
      this.url + '/'+ loginId +'/forgot',
       password,
       {headers:
        {skip:"true"},
        responseType: 'text',
      }
       
      );
  }

  handleError(eResponse: HttpErrorResponse) {
    if (eResponse.error instanceof ErrorEvent) {
      console.log('Client Side Error =' + eResponse.error.message);
      console.log('Status Code=' + eResponse.status);
    } else {
      console.log('Server Side Error =' + eResponse.error.message);
      console.log('Status Code=' + eResponse.status);
    }
    return throwError(eResponse.error);
  }
}

