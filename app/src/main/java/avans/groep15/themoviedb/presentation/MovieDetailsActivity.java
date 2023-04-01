package avans.groep15.themoviedb.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import avans.groep15.themoviedb.R;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView MovieTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        setTitle("Movie Name Details");


        //SET VIEWS
        MovieTitle = findViewById(R.id.text_view_mTitle);
        //GET INTENT
        Intent intent = getIntent();


        MovieTitle.setText(intent.getStringExtra("MovieTitle"));
    }


}
