import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute, NavigationStart, Params } from '@angular/router';
import { distinctUntilChanged, map } from 'rxjs';
import { Booking } from 'src/app/booking';
import { MovieService } from 'src/app/movie.service';

@Component({
  selector: 'app-book-ticket',
  templateUrl: './book-ticket.component.html',
  styleUrls: ['./book-ticket.component.css']
})
export class BookTicketComponent implements OnInit {
  ticketForm!: FormGroup;
  isReadOnly = true;
  bookingId!: string;
  displayElement: boolean = false;
  errorMessage!: string;
  booking!:Booking;
  availableSeats!:any;
  constructor(
    private fb: FormBuilder,
    private mService:MovieService,
    private router: Router,
    private actRouter: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.actRouter.params.subscribe(
      ( params:Params) => {
        this.bookingId=params['ticketId'];
         console.log(this.bookingId);
       });

       this.actRouter.params.pipe(
         map(params => params['ticketId']),
         distinctUntilChanged(),
       ).subscribe(
         changedParam => console.log(changedParam)
       )
       this.availableSeats=this.mService.getAllSeats(
        this.actRouter.snapshot.params['movieName'],
        this.actRouter.snapshot.params['theatreName']
       ).subscribe((data:any) =>{
         this.availableSeats=data;
       })

    this.displayElement=false;
    var obj=Object.values(sessionStorage);
    console.log(typeof obj[0]);
    console.log(Object.values(sessionStorage)[1]);
        this.ticketForm = this.fb.group({
          movieName:[{value:this.actRouter.snapshot.params['movieName'] , disabled:true}],
          theatreName:[{value:this.actRouter.snapshot.params['theatreName'] , disabled:true}],
          username:[{value:obj[1] , disabled:true}, Validators.required],
      noOfSeats: ['', Validators.required],
    });
  }

  addATicket(){
   // console.log(this.ticketForm.getRawValue());
    this.mService.addTicket(this.ticketForm.getRawValue()).subscribe(
      (res: any) => {
        console.log(this.bookingId)
        this.mService.setBookingId(res.ticketId);
      this.router.navigate(['ticket/viewTicket']);
      console.log(typeof res.ticketId);
      },
      (error: any) => {
        this.errorMessage = error;
        console.log(error);
      },
    );
  }
  
}
