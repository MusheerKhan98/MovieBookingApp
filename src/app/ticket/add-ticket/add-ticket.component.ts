import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Booking } from 'src/app/booking';
import { MovieService } from 'src/app/movie.service';
import { MoviePk } from 'src/app/ticket';

@Component({
  selector: 'app-add-ticket',
  templateUrl: './add-ticket.component.html',
  styleUrls: ['./add-ticket.component.css']
})
export class AddTicketComponent implements OnInit {
  addticketForm!: FormGroup;
  showAlert= false;
  availableSeats!:any;
  moviePk: MoviePk= {
    movieName:'',
    theatreName:''
  };
  displayElement: boolean = false;
  errorMessage!: string;
  booking!:Booking;
  constructor(
    private fb: FormBuilder,
    private mService:MovieService,
    private router: Router,
    private actRouter: ActivatedRoute
  ) {
  }
  ngOnInit(){
    console.log("HELLLLOOOOOOO")
    console.log(this.actRouter.snapshot.params['movieName']);
    this.moviePk.movieName=this.actRouter.snapshot.params['movieName'];
    this.moviePk.theatreName=this.actRouter.snapshot.params['theatreName'];
    this.addticketForm = this.fb.group({
      movieName:[{value:this.actRouter.snapshot.params['movieName'] , disabled:true}],
      theatreName:[{value:this.actRouter.snapshot.params['theatreName'] , disabled:true}],
  noOfSeats: ['', Validators.required],
});

this.availableSeats=this.mService.getAllSeats(
  this.actRouter.snapshot.params['movieName'],
  this.actRouter.snapshot.params['theatreName']
 ).subscribe((data:any) =>{
   this.availableSeats=data;
 })
  }
  addSeats(){
    this.mService.addSeats(this.addticketForm.controls['noOfSeats'].value,this.moviePk).subscribe(
      (res: any) => {
     // this.router.navigate(['ticket/viewTicket']);
     this.showAlert=true;
      },
      (error: any) => {
        this.errorMessage = error;
        console.log(error);
      },
    );
  }
}
