import { Pipe, PipeTransform } from '@angular/core';
import { Movie } from '../movie';
import { MovieResponse } from '../movie-response';

@Pipe({
  name: 'searchMovie',
})
export class SearchMoviePipe implements PipeTransform {
  transform(movies: MovieResponse[], searchFilter: string): MovieResponse[] {
    if (!movies || !searchFilter) {
      return movies;
    } else {
      return movies.filter((movie) =>
        movie.moviePK.movieName
          .toLocaleLowerCase()
          .includes(searchFilter.toLocaleLowerCase())
      );
    }
  }
}
