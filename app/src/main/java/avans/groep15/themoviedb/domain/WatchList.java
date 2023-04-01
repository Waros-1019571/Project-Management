package avans.groep15.themoviedb.domain;


import java.util.ArrayList;
import java.util.List;

public class WatchList {
    private int id; //AutoIncrement SQL
    private String title;
    private String description;
    private List<Movie> movies = new ArrayList<>();

    public WatchList(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getItems(){
        return movies.size();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}
