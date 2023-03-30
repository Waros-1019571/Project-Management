package avans.groep15.themoviedb.application.listeners;

import androidx.lifecycle.LifecycleOwner;

import java.util.List;

import avans.groep15.themoviedb.domain.Movie;

public interface MovieListener extends LifecycleOwner {
    void hasLoaded(List<Movie> movies);
}
