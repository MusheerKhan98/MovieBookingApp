import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Booking } from 'src/app/booking';
import { MovieService } from 'src/app/movie.service';
import { Ticket } from 'src/app/ticket';

@Component({
  selector: 'app-detail-view-ticket',
  templateUrl: './detail-view-ticket.component.html',
  styleUrls: ['./detail-view-ticket.component.css']
})
export class DetailViewTicketComponent implements OnInit {
  ticket!: Ticket;
  bookingId!: string;
  constructor(
    private actRouter: ActivatedRoute,
    private router: Router,
    private mService: MovieService
  ) {
   
    console.log(this.bookingId);
  }

  ngOnInit(): void {
    console.log("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
       this.bookingId=this.mService.getBookingId();
       this.mService.getBookingById(this.bookingId).subscribe((data: Ticket) => {
        this.ticket = data;
        console.log(this.ticket);
       }
       );
    
  }
}
