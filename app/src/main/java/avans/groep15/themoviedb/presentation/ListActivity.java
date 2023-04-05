package avans.groep15.themoviedb.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
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

    private final ListRepository listRepository = ListRepository.getInstance();
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.d(TAG, "Loading watch lists");

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

        loadDataIntoRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Reloading watch lists");
        loadDataIntoRecyclerView();
    }

    private void loadDataIntoRecyclerView() {
        List<WatchList> lists = listRepository.getWatchListObservable().getValue();
        if (lists != null) {
            RecyclerView recyclerViewList = findViewById(R.id.recyclerViewList);
            ListAdapter listAdapter = new ListAdapter(this, lists);
            recyclerViewList.setAdapter(listAdapter);
            recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}