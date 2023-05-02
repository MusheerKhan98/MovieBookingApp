export interface Ticket {
    ticketId: string
    noOfSeats: number
    movie: Movie
    theatre: string
    seatNumber: number[]
    username: string
  }
  
  export interface Movie {
    moviePK: MoviePk
    noOfTickets: number
    movieGenre: string
    movieHours: string
    movieLanguage: string
    movieDescription: string
    movieRating: string
    movieDate: string
    ticketStatus: boolean
  }
  
  export interface MoviePk {
    movieName: string
    theatreName: string
  }
  