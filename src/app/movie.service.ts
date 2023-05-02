import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { buffer, catchError, Observable, skip, throwError } from 'rxjs';
import { Booking } from './booking';
import { Movie, MoviePk } from './movie';
import { MovieResponse } from './movie-response';
import { Ticket } from './ticket';


@Injectable({
  providedIn: 'root'
})
export class MovieService {
  public bookingId!: string;
  public booking!:Booking
  private url = 'http://localhost:8085/api/v1.0/moviebooking';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };
  httpOptions1 = {
    headers: new HttpHeaders({
      'Content-Type': 'multipart/mixed',
    }),
  };

  constructor(private httpClient : HttpClient) { }

  setBookingId(bookingId:string) {
     this.bookingId=bookingId;
  }
  getBookingId() {
    return this.bookingId;
 }

  getAllMovies(): Observable<MovieResponse[]> {
    return this.httpClient
      .get<MovieResponse[]>(
        this.url + '/all',
        this.httpOptions
        )
      .pipe(catchError(this.handleError));
  }

  getMovieById(movieName: any, theatreName:any): Observable<MovieResponse> {
    return this.httpClient
      .get<MovieResponse>(
        this.url + '/viewMovie/' + movieName+'/'+theatreName,
        {headers:
          {skip:"true"}
        }
        )
      .pipe(catchError(this.handleError));
  }

  addMovie(mvee: FormData): Observable<any> {
    return this.httpClient
      .post<Movie>(
        this.url + '/addMovie',
        mvee
      )
      .pipe(catchError(this.handleError));
  }

  addTicket(tkt: any): Observable<Booking> {
    console.log(tkt);
    console.log(JSON.stringify(tkt));
    console.log( this.url + '/add');
    return this.httpClient
      .post<Booking>(
        this.url + '/add',
        JSON.stringify(tkt),
        this.httpOptions
      )
      .pipe(catchError(this.handleError));
  }

  getBookingById(bookingId: any): Observable<Ticket> {
    console.log(bookingId);
    console.log(this.url + '/viewTicket/' + bookingId);
    return this.httpClient
      .post<Ticket>(
        this.url + '/viewTicket' , bookingId ,this.httpOptions
        )
      .pipe(catchError(this.handleError));
  }
  addSeats(noOfSeats:number,moviePk:MoviePk) {
    return this.httpClient.put(
      this.url + '/update/'+ noOfSeats,
      moviePk,
      this.httpOptions
       
      );
  }

  getAllSeats(movieName:string, theatreName:string): Observable<any[]> {
    return this.httpClient
      .get<any[]>(this.url + '/'+movieName + '/'+theatreName+ '/viewAvailableSeats')
      .pipe(catchError(this.handleError));
  }

  deleteMovie(movieName: string,theatreName:string): Observable<Movie> {
    return this.httpClient
      .delete<Movie>(this.url + '/delete/' + movieName + '/' + theatreName)
      .pipe(catchError(this.handleError));
  }

  handleError(eResponse: HttpErrorResponse) {
    if (eResponse.error instanceof ErrorEvent) {
      console.log('Client Side Error =' + eResponse.error.message);
      console.log('Status Code=' + eResponse.status);
    } else {
      console.log('Server Side Error =' + eResponse);
      console.log('Status Code=' + eResponse);
    }
    return throwError(eResponse.error);
  }
}
