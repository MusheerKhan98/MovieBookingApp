import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Login } from 'src/app/login';
import { LoginService } from 'src/app/login.service';
import { Movie } from 'src/app/movie';
import { MovieResponse } from 'src/app/movie-response';
import { MovieService } from 'src/app/movie.service';

@Component({
  selector: 'app-all-movies',
  templateUrl: './all-movies.component.html',
  styleUrls: ['./all-movies.component.css']
})
export class AllMoviesComponent implements OnInit{
  movies: MovieResponse[] = [];
  imageUrl:any;
  genre!: string;
  searchArray: any[] = [];
  sortArray: any[] = [];
  movieFilterValue!: string;
  constructor(private mService: MovieService,
    public sanitizer:DomSanitizer,
    private router:Router,
    public lService : LoginService
    
    ) {}

  ngOnInit(): void {
    this.mService.getAllMovies().subscribe((data: MovieResponse[]) => {
      console.log(data);
      this.movies = data;
      this.searchArray = data;
      this.sortArray = data;
    }
    )
      this.imageUrl='data:image/png;base64,'
      +this.movies[5].movieImage.image.data;
   
  }
  topRated() {
    this.movies = this.sortArray
      .sort((a, b) => (a.movieRating > b.movieRating ? -1 : 1))
      .slice(0, 4);
  }

  searchByGenre() {
    this.movies = this.searchArray.filter(
      (data) => data.movieGenre == this.genre
    );
  }

  latestMovies() {
    this.movies = this.sortArray.sort((a, b) =>
      a.movieDate > b.movieDate ? -1 : 1
    );
  }
  allMovies() {
    this.ngOnInit();
  }
  removeMovie(movieName: string, theatreName:string) {
    this.mService.deleteMovie(movieName,theatreName).subscribe((data: any) => {
      //this.ngOnInit();
      console.log(data);
      this.allMovies();
     // this.router.navigate(['/movie/admin/list']);
    });
  }

  log(item:any){
    console.log(item);
  }
}
