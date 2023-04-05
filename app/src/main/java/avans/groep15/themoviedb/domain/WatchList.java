package avans.groep15.themoviedb.domain;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class WatchList {
    private int id;
    private String name;
    private String description;
    private final String language = "en";
    private int item_count = 0; // List size according to the API
    private List<Movie> movies = new ArrayList<>();

    public WatchList(int id, String name, String description, int item_count) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.item_count = item_count;
    }

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
        // Prevent Retrofit from overriding empty list with null
        if (movies == null) {
            movies = new ArrayList<>();
        }
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        // Prevent Retrofit from overriding empty list with null
        if (movies != null) {
            this.movies = movies;
        }
    }

    public int getItems() {
        // Get either local or API list size
        return Math.max(getMovies().size(), item_count);
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
