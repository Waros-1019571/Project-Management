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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.application.asynctasks.GetMovieTask;
import avans.groep15.themoviedb.application.listeners.MovieListener;
import avans.groep15.themoviedb.datastorage.AccountRepository;
import avans.groep15.themoviedb.domain.Movie;

public class MainActivity extends AppCompatActivity implements MovieListener {

    private SearchView searchView;
    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private AccountRepository accountRepository = AccountRepository.getInstance();

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                // Handle Home click
                return true;
            case R.id.account:
                Intent accountIntent = new Intent(this, LoginActivity.class);
                startActivity(accountIntent);
                return true;
            case R.id.lists:
                // Start ListActivity
                if (accountRepository.getSessionIdObservable().getValue() == null) {
                    Toast.makeText(this, "You need to be logged in first", Toast.LENGTH_SHORT).show();
                    return true;
                }
                Intent listIntent = new Intent(this, ListActivity.class);
                startActivity(listIntent);
                return true;
            case R.id.settings:
                // Handle Settings click
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetMovieTask(this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String genre = parent.getItemAtPosition(position).toString();
        List<Movie> filteredMovies = filterMoviesByGenre(genre);
        movieAdapter.setMovies(filteredMovies); // Set the filtered movies on the adapter
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    private List<Movie> filterMoviesByGenre(String genre) {
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenres().contains(genre)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }
}