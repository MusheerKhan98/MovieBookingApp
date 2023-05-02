import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { FileHandleModel } from 'src/app/file-handle-model';
import { Movie } from 'src/app/movie';
import { MovieService } from 'src/app/movie.service';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {
  errorBool=false;
  movie:Movie = {
    moviePK: {
      movieName:'',
      theatreName:''
    },
    noOfTickets: 0,
    movieGenre: '',
    movieHours: '',
    movieLanguage: '',
    movieDescription: '',
    movieRating: '',
    movieDate: {},
    ticketStatus: false,
    movieImage: []
  };
  mveeForm!: FormGroup;
  errorMessage!: string;


  constructor(
    private fb: FormBuilder,
    private mService: MovieService,
    private router: Router,
    private sanitizer : DomSanitizer
  ) {}

  ngOnInit(): void {
    this.mveeForm = this.fb.group({
      movieName: ['', Validators.required],
      theatreName: ['', Validators.required],
      movieHours: ['', Validators.required],
      movieGenre: ['', Validators.required],
      movieLanguage: ['', Validators.required],
      movieDescription: ['', Validators.required],
      movieRating: ['', Validators.required],
      movieDate: [''],
      noOfTickets:['', Validators.required]
    });
  }

  addAMovie() {
    const preparedFormData = this.prepareFormData(this.mveeForm.value,this.movie);
    this.mService.addMovie(preparedFormData).subscribe(
      (res: any) => {
        this.router.navigate(['']);
      },
      (error: any) => {
        this.errorMessage = error;
        this.errorBool = true;
      }
    );
  }
  onFileSelect(event:any){
   if(event.target.files ){
    const file = event.target.files[0];
    const fileHandle: FileHandleModel = {
      file:file,
      url: this.sanitizer.bypassSecurityTrustUrl(
        window.URL.createObjectURL(file)
      )
    }
    console.log(fileHandle.file);
    this.movie.movieImage.push(fileHandle);
  }

}

  prepareFormData(mveeForm: FormGroup, movie:Movie){

    console.log(this.movie.movieImage);
    const formData = new FormData();
    formData.append(
      'movieRequest',
      new Blob([JSON.stringify(this.mveeForm.value)],{type: 'application/json'})
      );
      console.log(this.movie.movieImage.length)
      if(this.movie.movieImage.length != 0){
    formData.append(
      'imageFile',
      this.movie.movieImage[0].file,
      this.movie.movieImage[0].file.name
    )   
      }
    return formData;  
  
}
  
}

