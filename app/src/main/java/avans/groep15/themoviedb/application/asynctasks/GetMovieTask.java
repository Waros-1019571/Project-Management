package avans.groep15.themoviedb.application.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.Observer;

import java.util.List;

import avans.groep15.themoviedb.application.listeners.MovieListener;
import avans.groep15.themoviedb.datastorage.MovieRepository;
import avans.groep15.themoviedb.domain.Movie;

public class GetMovieTask extends AsyncTask<Void, Void, List<Movie>> {
    private final static String TAG = GetMovieTask.class.getSimpleName();
    private final MovieRepository repository = MovieRepository.getInstance();

    public GetMovieTask(MovieListener movieListener) {
        this.repository.getMoviesObservable().observe(movieListener, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                Log.d(TAG, "Parsing movies to movie listener");
                movieListener.hasLoaded(movies);
            }
        });
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        Log.d(TAG, "Getting popular movies");
        repository.performGetMovies();
        return null;
    }


}
