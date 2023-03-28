package avans.groep15.themoviedb.domain.responses;

import java.util.List;

import avans.groep15.themoviedb.domain.Movie;

public class MovieResult {
    private final List<Movie> results;

    public MovieResult(List<Movie> results) {
        this.results = results;
    }

    public List<Movie> getResults() {
        return results;
    }
}
