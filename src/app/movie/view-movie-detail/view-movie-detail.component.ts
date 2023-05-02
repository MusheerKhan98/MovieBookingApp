import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/login.service';
import { Movie } from 'src/app/movie';
import { MovieResponse } from 'src/app/movie-response';
import { MovieService } from 'src/app/movie.service';

@Component({
  selector: 'app-view-movie-detail',
  templateUrl: './view-movie-detail.component.html',
  styleUrls: ['./view-movie-detail.component.css']
})
export class ViewMovieDetailComponent implements OnInit {
  mvee!: MovieResponse;
  movieName!: string;
  theatreName!: string;
  constructor(
    private actRouter: ActivatedRoute,
    private router: Router,
    private mService: MovieService,
    public lService:LoginService
  ) {}

  ngOnInit(): void {
    this.movieName = this.actRouter.snapshot.params['movieName'];
    this.theatreName=this.actRouter.snapshot.params['theatreName']
    this.mService.getMovieById(this.movieName,this.theatreName).subscribe((data: MovieResponse) => {
      this.mvee = data;
    });
  }
}
