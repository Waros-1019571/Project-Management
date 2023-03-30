package avans.groep15.themoviedb.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.domain.Movie;
import avans.groep15.themoviedb.domain.responses.MovieResult;

public class ListActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerViewList;
    private MovieAdapter movieAdapterList;
    private MovieResult movieResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerViewList = findViewById(R.id.recyclerViewList);
        movieAdapterList = new MovieAdapter(this, movies);
        recyclerViewList.setAdapter(movieAdapterList);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
    }


}