package avans.groep15.themoviedb.domain;

import java.util.List;

public class Movie {
    private String title;
    private String description;
    private double rating;
    private String imageUrl;
    private List<String> genres;
    private List<String> actors;
    private String releaseDate;
    private String status;  // Hier kan je een enum voor gebruiken of boolean - true false (welke statussen zijn er?)



    public Movie(String title, double rating, String imageUrl, List<String> genres, String releaseDate) {
        this.title = title;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.genres = genres;
        this.releaseDate = releaseDate;
    }

    public Movie(String title, String description, double rating, String imageUrl, List<String> genres, List<String> actors, String releaseDate, String status) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.genres = genres;
        this.actors = actors;
        this.releaseDate = releaseDate;
        this.status = status;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
