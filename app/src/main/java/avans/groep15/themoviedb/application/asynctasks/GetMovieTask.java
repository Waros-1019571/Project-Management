package avans.groep15.themoviedb.application.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.Observer;

import java.util.List;

import avans.groep15.themoviedb.application.listeners.MovieListener;
import avans.groep15.themoviedb.datastorage.MovieRepository;
import avans.groep15.themoviedb.domain.Movie;
import avans.groep15.themoviedb.datastorage.database.MovieDao;
import avans.groep15.themoviedb.datastorage.database.MovieDatabase;

public class GetMovieTask extends AsyncTask<Void, Void, List<Movie>> {
    private final static String TAG = GetMovieTask.class.getSimpleName();
    private final MovieRepository repository = MovieRepository.getInstance();
    private Context context;

    public GetMovieTask(Context context, MovieListener movieListener) {
        this.context = context;
        this.repository.getMoviesObservable().observe(movieListener, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                Log.d(TAG, "Parsing movies to movie listener");
                new InsertMoviesTask(context, movies).execute();
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


    private static class InsertMoviesTask extends AsyncTask<Void, Void, Void> {
        private final MovieDao movieDao;
        private final List<Movie> movies;

        public InsertMoviesTask(Context context, List<Movie> movies) {
            MovieDatabase movieDatabase = MovieDatabase.getInstance(context);
            // Get an instance of the movie DAO
            movieDao = movieDatabase.movieDao();
            this.movies = movies;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Insert each movie into the database
            for (Movie movie : movies) {
                movieDao.insert(movie);
            }
            return null;
        }
    }
}
