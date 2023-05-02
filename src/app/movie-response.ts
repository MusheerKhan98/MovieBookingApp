export interface MovieResponse {
    moviePK: MoviePk
    noOfTickets: number
    movieGenre: string
    movieHours: string
    movieLanguage: string
    movieDescription: string
    movieRating: string
    movieDate: any
    ticketStatus: boolean
    movieImage: MovieImage
  }
  
  export interface MoviePk {
    movieName: string
    theatreName: string
  }
  
  export interface MovieImage {
    id: string
    title: string
    image: Image
  }
  
  export interface Image {
    type: number
    data: string
  }