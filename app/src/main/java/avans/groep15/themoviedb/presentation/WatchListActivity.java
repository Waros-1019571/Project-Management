package avans.groep15.themoviedb.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.domain.Movie;
import avans.groep15.themoviedb.domain.WatchList;

public class WatchListActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerViewList;
    private MovieAdapter listAdapterDetails;
    private WatchList watchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);

        Intent intent = getIntent();

        recyclerViewList = findViewById(R.id.recyclerView);

        ArrayList<Movie> movies = (ArrayList<Movie>) intent.getSerializableExtra("movieList");
        movies.add(new Movie("Title1", 10));
        movies.add(new Movie("Title2", 5));


        listAdapterDetails = new MovieAdapter(this, movies);
        recyclerViewList.setAdapter(listAdapterDetails);
        recyclerViewList.setLayoutManager(new GridLayoutManager(this, 2));
    }
}