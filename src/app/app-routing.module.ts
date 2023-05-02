import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/LoginComponent';
import { AddMovieComponent } from './movie/add-movie/add-movie.component';
import { AllMoviesComponent } from './movie/all-movies/all-movies.component';
import { MovieComponent } from './movie/movie.component';
import { ViewMovieDetailComponent } from './movie/view-movie-detail/view-movie-detail.component';
import { RegisterComponent } from './register/register.component';
import { AddTicketComponent } from './ticket/add-ticket/add-ticket.component';
import { BookTicketComponent } from './ticket/book-ticket/book-ticket.component';
import { DetailViewTicketComponent } from './ticket/detail-view-ticket/detail-view-ticket.component';
import { TicketComponent } from './ticket/ticket.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  { path: 'signIn', component: LoginComponent },
  {path:'signUp', component:RegisterComponent},
  {path:'forgot', component:ForgotPasswordComponent},
  {
    path: 'movie',
    component: MovieComponent,
    children: [
      { path: '', component: AllMoviesComponent },
      { path: 'view/:movieName/:theatreName', component: ViewMovieDetailComponent },
      {path:'add', component:AddMovieComponent},
     ]

},
    {
      path:'ticket',
      component:TicketComponent,
      canActivate: [AuthGuard],
      children: [
        { path: ':movieName/:theatreName', component: BookTicketComponent },
        { path: 'viewTicket', component: DetailViewTicketComponent },
        { path:'addSeats/:movieName/:theatreName', component:AddTicketComponent}
      ]
    }
]
 

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
