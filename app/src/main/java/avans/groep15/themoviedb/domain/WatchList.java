package avans.groep15.themoviedb.domain;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class WatchList {
    private int id;
    private String name;
    private String description;
    private final String language = "en";
    private List<Movie> movies = new ArrayList<>();

    public WatchList(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
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

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
