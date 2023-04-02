package avans.groep15.themoviedb.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.domain.Movie;
import avans.groep15.themoviedb.domain.WatchList;
import avans.groep15.themoviedb.domain.responses.MovieResult;

public class ListActivity extends AppCompatActivity {

    private ArrayList<WatchList> lists;
    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerViewList;
    private ListAdapter listAdapter;
    private MovieResult movieResult;

    private WatchList watchList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ///GET WATCHLIST FROM API
        //CODE FOR THAT




        //CREATED TEMP LIST AND ADDED MOVIES --> SENDS TO LISTADAPTER
        List<WatchList> tempList = new ArrayList<>();

        WatchList list2 = new WatchList("List2", "Description");
        list2.addMovie(new Movie("Title1", 10));

        WatchList list1 = new WatchList("List 1", "Description");
        list1.addMovie(new Movie("Title2", 9));
        list1.addMovie(new Movie("Title3", 8));

        tempList.add(list1);
        tempList.add(list2);

        //MORE LOG A LIKES
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