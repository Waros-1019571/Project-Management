package avans.groep15.themoviedb.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.domain.Movie;

public class MovieActivity extends AppCompatActivity {

    private TextView movieTitle;
    private TextView movieDescription;
    private ImageView moviePoster;
    private TextView movieGenre;
    private TextView movieRating;
    private TextView movieAge;
    private TextView movieReleaseDate;
    private TextView movieNativeLanguage;
    private TextView movieActor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        setTitle("Movie Details");

        // SET VIEWS
        movieTitle = findViewById(R.id.text_view_mTitle);
        movieDescription = findViewById(R.id.text_view_mDescription);
        moviePoster = findViewById(R.id.image_view_mPoster);
        movieGenre = findViewById(R.id.text_view_mGenre);
        movieRating = findViewById(R.id.text_view_mRating);
        movieAge = findViewById(R.id.text_view_mAge);
        movieReleaseDate = findViewById(R.id.text_view_mReleaseDate);
        movieNativeLanguage = findViewById(R.id.text_view_mNativeLanguage);


        // GET INTENT
        Intent intent = getIntent();

        // SET MOVIE DETAILS
        movieTitle.setText(intent.getStringExtra("MovieTitle"));
        movieDescription.setText(intent.getStringExtra("MovieDescription"));


        String posterPath = getIntent().getStringExtra("MoviePoster");
        System.out.println(posterPath);
        String posterUrl = "https://image.tmdb.org/t/p/original/" + posterPath;
        Glide.with(this)
                .load(posterUrl)
                .into(moviePoster);


movieGenre.setText(intent.getStringExtra("MovieGenre"));



        movieRating.setText("Rating: " + intent.getStringExtra("MovieRating"));



        //For age in numbers
//        String age = "";
//        if (intent.getStringExtra("MovieAge").equalsIgnoreCase("true")) {
//            age = "18+";
//        } else {
//            age = "All ages";
//        }


        movieAge.setText("18+: " + intent.getStringExtra("MovieAge"));

        String movieRelease = intent.getStringExtra("MovieRelease");
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
            movieReleaseDate.setText("Release Date: " + formattedDate);
        } else {
            movieReleaseDate.setText("Release Date: N/A");
        }

        movieNativeLanguage.setText("Language: " + intent.getStringExtra("MovieNativeLanguage"));


    }
}
