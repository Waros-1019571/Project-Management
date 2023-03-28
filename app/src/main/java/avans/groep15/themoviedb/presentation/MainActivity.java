package avans.groep15.themoviedb.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
     //   MenuItem menuItem = findViewById(R.id.dropdown_item1);

//        View actionView = menuItem.getActionView();

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //temp genres
        List<String> genres = new ArrayList<>();
        genres.add("Action");
        genres.add("Comedy");
        genres.add("Drama");


        //temp list
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            movieList.add(new Movie("Movie 1", 10.0, "https://image.tmdb.org/t/p/w500/abc123.jpg", genres, "10-04-2012"));
            movieList.add(new Movie("Movie 2", 6.0, "https://image.tmdb.org/t/p/w500/abc123.jpg", genres, "11-09-2020"));
            movieList.add(new Movie("Movie 3", 7.5, "https://image.tmdb.org/t/p/w500/abc123.jpg", genres, "03-02-2018"));
        }

        recyclerView = findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter(this, movieList);
        //   movieAdapter = new MovieAdapter(this, movies);
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