package avans.groep15.themoviedb.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.domain.Movie;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movies;
    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            List<String> genres = new ArrayList<>();
            genres.add("Action");
            genres.add("Comedy");
            genres.add("Drama");


            List<Movie> movieList = new ArrayList<>();
            movieList.add(new Movie("Movie 1", 10.0, "https://image.tmdb.org/t/p/w500/abc123.jpg", genres, "10-04-2012"));
            movieList.add(new Movie("Movie 2", 6.0, "https://image.tmdb.org/t/p/w500/abc123.jpg", genres, "11-09-2020"));
            movieList.add(new Movie("Movie 3", 7.5, "https://image.tmdb.org/t/p/w500/abc123.jpg", genres, "03-02-2018"));


            recyclerView = findViewById(R.id.recyclerView);
            movieAdapter = new MovieAdapter(this, movieList);
            recyclerView.setAdapter(movieAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        }


//    public void hasLoaded(List<Movie> Movie) {
//        Log.i(TAG, "Binding list of movies to recycler view");
//
//        recyclerView.findViewById(R.id.recyclerView);
//        movieAdapter = new MovieAdapter(this, movies);
//        recyclerView.setAdapter(movieAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//
//    }
    }