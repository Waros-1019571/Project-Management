package avans.groep15.themoviedb.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {
    private int id;
    private String original_title;
    private String overview;
    private double vote_average;
    private Date release_date;
    private String poster_path;
    private List<String> genres;
    private List<String> actors;
    private String status; //Enum / boolean
    private boolean adult;
    private String original_language;
    private String comment;

    public Movie(int id, String original_title, String overview, List<String> genres, double vote_average, boolean adult, Date release_date, String poster_path, String original_language, String comment) {
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.genres = genres;
        this.vote_average = vote_average;
        this.adult = adult;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.original_language = original_language;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public boolean getAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}

