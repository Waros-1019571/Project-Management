package avans.groep15.themoviedb.application.asynctasks;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import avans.groep15.themoviedb.domain.Movie;
import avans.groep15.themoviedb.domain.datanase.MovieDatabase;

public class GetDatabaseMovies extends AsyncTask<Void, Void, List<Movie>> {
    private MovieDatabase database;
    private MutableLiveData<List<Movie>> liveData;

    public GetDatabaseMovies(MovieDatabase database) {
        this.database = database;
        this.liveData = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMoviesLiveData() {
        return liveData;
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
//        return database.ge();
        return null;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        liveData.setValue(movies);
    }
}

///CAN POSSIBLY DELETE