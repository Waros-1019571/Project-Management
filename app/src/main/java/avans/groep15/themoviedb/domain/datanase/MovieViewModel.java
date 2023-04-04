package avans.groep15.themoviedb.domain.datanase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import avans.groep15.themoviedb.datastorage.MovieRepository;
import avans.groep15.themoviedb.domain.Movie;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repository;
    private LiveData<List<Movie>> allMeals;


    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository();
     //   allMeals = repository.getMoviesLiveData();
    }
    public void insert(Movie movie) {
      //  repository.insert(movie);
    }
}


///CAN POSSIBLY DELETE
