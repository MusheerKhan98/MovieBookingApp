import { Component, OnInit } from '@angular/core';
import { Movie } from '../movie';
import { MovieResponse } from '../movie-response';
import { MovieService } from '../movie.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  topRatedMovies: MovieResponse[] = [];
  latestMovies: MovieResponse[] = [];
  constructor(private mService: MovieService) {}

  ngOnInit(): void {
    this.mService.getAllMovies().subscribe((data: MovieResponse[]) => {
      this.topRatedMovies = data
        .sort((a, b) => (a.movieRating > b.movieRating ? -1 : 1))
        .slice(0, 8);
    });
    this.mService.getAllMovies().subscribe((data: MovieResponse[]) => {
      this.latestMovies = data
        .sort((a, b) => (a.movieDate > b.movieDate ? -1 : 1))
        .slice(0, 3);
    });
  }

}
