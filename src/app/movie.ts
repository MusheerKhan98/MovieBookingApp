import { FileHandleModel } from "./file-handle-model";

export interface Movie {
    moviePK: MoviePk
    noOfTickets: number
    movieGenre: string
    movieHours: string
    movieLanguage: string
    movieDescription: string
    movieRating: string
    movieDate: any
    ticketStatus: boolean
    movieImage: any[]
  }
  
  export interface MoviePk {
    movieName: string
    theatreName: string
  }
  