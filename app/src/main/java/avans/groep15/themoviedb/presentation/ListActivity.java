package avans.groep15.themoviedb.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.datastorage.ListRepository;
import avans.groep15.themoviedb.domain.Movie;
import avans.groep15.themoviedb.domain.WatchList;
import avans.groep15.themoviedb.domain.responses.MovieResult;

public class ListActivity extends AppCompatActivity {

    private ListRepository listRepository = ListRepository.getInstance();
    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerViewList;
    private ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerViewList = findViewById(R.id.recyclerViewList);

        // Add onClick to add button
        FloatingActionButton actionButton = findViewById(R.id.addListButton);
        ListActivity thisContext = this;
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisContext, AddListActivity.class);
                startActivity(intent);
            }
        });






        //CREATED TEMP LIST AND ADDED MOVIES --> SENDS TO LISTADAPTER
        List<WatchList> tempList = new ArrayList<>();

        WatchList list2 = new WatchList("List2", "Description");
        list2.addMovie(new Movie("Title1", 10));

        WatchList list1 = new WatchList("List 1", "Description");
        list1.addMovie(new Movie("Title2", 9));
        list1.addMovie(new Movie("Title3", 8));

        tempList.add(list1);
        tempList.add(list2);


        List<Movie> movies = list2.getMovies();
        for (Movie movie : movies) {
            System.out.println("Movie: " + movie.getOriginal_title() + " - " + movie.getVote_average());
        }

        recyclerViewList = findViewById(R.id.recyclerViewList);
        //CHANGE TEMPLIST WITH MAIN LIST.
        listAdapter = new ListAdapter(this, tempList);
        recyclerViewList.setAdapter(listAdapter);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
    }
}