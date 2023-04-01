package avans.groep15.themoviedb.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.application.asynctasks.GetMovieTask;
import avans.groep15.themoviedb.application.listeners.MovieListener;
import avans.groep15.themoviedb.domain.Movie;
import avans.groep15.themoviedb.domain.responses.MovieResult;

public class MainActivity extends AppCompatActivity implements MovieListener {


    private SearchView searchView;
    private ArrayList<Movie> movies;
    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                // Handle Home click
                return true;
            case R.id.account:
                // Handle Account click
                return true;
            case R.id.lists:
                // Start ListActivity
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                return true;
            case R.id.settings:
                // Handle Settings click
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private MovieResult movieResult;


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
        new GetMovieTask(this).execute();



    }


    @Override
    public void hasLoaded(List<Movie> movies) {
        for (Movie movie : movies) {
            Log.d("movie", movie.getOriginal_title());
        }
        recyclerView = findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Update your adapter's data with the new search query
                movieAdapter.filter(newText);
                return false;
            }
        });
    }
}

