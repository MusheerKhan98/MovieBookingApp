import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from "./login/LoginComponent";
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MovieComponent } from './movie/movie.component';
import { SearchMoviePipe } from './movie/search-movie.pipe';
import { ViewMovieDetailComponent } from './movie/view-movie-detail/view-movie-detail.component';
import { AllMoviesComponent } from './movie/all-movies/all-movies.component';
import { BasicAuthHttpInterceptorService } from './basic-auth-http-interceptor.service';
import { AddMovieComponent } from './movie/add-movie/add-movie.component';
import { TicketComponent } from './ticket/ticket.component';
import { BookTicketComponent } from './ticket/book-ticket/book-ticket.component';
import { DetailViewTicketComponent } from './ticket/detail-view-ticket/detail-view-ticket.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { AddTicketComponent } from './ticket/add-ticket/add-ticket.component';
import {MatGridListModule} from '@angular/material/grid-list';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    MovieComponent,
    SearchMoviePipe,
    ViewMovieDetailComponent,
    AllMoviesComponent,
    AddMovieComponent,
    TicketComponent,
    BookTicketComponent,
    DetailViewTicketComponent,
    ForgotPasswordComponent,
    AddTicketComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatGridListModule
  ],
  providers: [
    {  
      provide:HTTP_INTERCEPTORS, useClass:BasicAuthHttpInterceptorService, multi:true 
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
