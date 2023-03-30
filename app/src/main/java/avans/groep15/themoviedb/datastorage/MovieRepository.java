package avans.groep15.themoviedb.datastorage;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Collections;
import java.util.List;

import avans.groep15.themoviedb.domain.Movie;
import avans.groep15.themoviedb.domain.responses.MovieResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository extends Repository {
    private final static String TAG = MovieRepository.class.getSimpleName();
    private static volatile MovieRepository instance;
    private final MutableLiveData<List<Movie>> movies;

    private MovieRepository() {
        movies = new MutableLiveData<>();
    }

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    public LiveData<List<Movie>> getMoviesObservable() {
        return this.movies;
    }

    public void performGetMovies() {
        Log.d(TAG, "Getting movies");
        ApiService api = super.getApiService();
        Call<MovieResult> call = api.getMovies("277fca8f75771911582af3d12b45b08d");

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {
                if (response.body() == null) {
                    Log.e(TAG, "Error getting movies: no response body");
                    return;
                }
                Collections.reverse(response.body().getResults());
                movies.setValue(response.body().getResults());
                Log.i(TAG, "Received list of popular movies");
            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {
                Log.e(TAG, "Error getting movies: " + t.getMessage());
            }
        });
    }


}
