package avans.groep15.themoviedb.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Movie implements Serializable {
    private int id;
    private String original_title;
    private double vote_average;
    private Date release_date;
    private String poster_path;
    private List<String> genres;
    private List<String> actors;
    private String status; //Enum / boolean

    public Movie(int id, String original_title, double vote_average, Date release_date, String poster_path, List<String> genres) {
        this.id = id;
        this.original_title = original_title;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.genres = genres;
    }



    public Movie(String original_title, double vote_average) {
        this.original_title = original_title;
        this.vote_average = vote_average;
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

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
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


    @NonNull
    @Override
    public String toString() {
        return this.original_title;
    }
}

