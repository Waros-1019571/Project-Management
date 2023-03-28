package avans.groep15.themoviedb.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.application.asynctasks.GetMovieTask;
import avans.groep15.themoviedb.application.listeners.MovieListener;
import avans.groep15.themoviedb.domain.Movie;

public class MainActivity extends AppCompatActivity implements MovieListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetMovieTask(this).execute();
    }

    @Override
    public void hasLoaded(List<Movie> movies) {
        for (Movie movie : movies) {
            Log.d("movie", movie.getOriginal_title());
        }
    }
}