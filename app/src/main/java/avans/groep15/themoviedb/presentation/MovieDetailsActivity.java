package avans.groep15.themoviedb.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import avans.groep15.themoviedb.R;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView MovieTitle;
    private TextView MovieRelease;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        setTitle("Movie Name Details");


        //SET VIEWS
        MovieTitle = findViewById(R.id.text_view_mTitle);
        MovieRelease = findViewById(R.id.text_view_mReleaseDate);
        //GET INTENT
        Intent intent = getIntent();

        MovieTitle.setText(intent.getStringExtra("MovieTitle"));


        String movieRelease = intent.getStringExtra("MovieRelease");
        System.out.println(movieRelease);

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        if (movieRelease != null) {
            Date date = null;
            try {
                date = inputDateFormat.parse(movieRelease.toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String formattedDate = outputDateFormat.format(date);
            MovieRelease.setText(formattedDate);
        } else {
           MovieRelease.setText("N/A");
        }

    }


}
