package avans.groep15.themoviedb.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
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

    private ArrayList<Movie> movies;
    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private MovieResult movieResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetMovieTask(this).execute();




//        recyclerView = findViewById(R.id.recyclerView);
//        movieAdapter = new MovieAdapter(this, movieList);
//        //   movieAdapter = new MovieAdapter(this, movies);
//        recyclerView.setAdapter(movieAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

//        movieResult = new ViewModelProvider(this).get(MovieResult.class);
//        movieResult.getResults().observe(this, movieAdapter::);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MainActivity thisActivity = this;
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //   MenuItem menuItem = findViewById(R.id.dropdown_item1);
        //   View actionView = menuItem.getActionView();
        MenuItem account = menu.findItem(R.id.account);
        account.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                Intent intent = new Intent(thisActivity, AccountActivity.class);
                startActivity(intent);
                return false;
            }
        });
        return true;
    }
    @Override
    public void hasLoaded(List<Movie> movies) {
        for (Movie movie : movies) {
            Log.d("movie", movie.getOriginal_title());
        }
        recyclerView = findViewById(R.id.recyclerView);
        movieAdapter = new MovieAdapter(this, movies);
        //   movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }
}

