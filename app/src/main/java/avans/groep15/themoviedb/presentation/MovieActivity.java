package avans.groep15.themoviedb.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.application.asynctasks.AddToListTask;
import avans.groep15.themoviedb.application.listeners.StatusListener;
import avans.groep15.themoviedb.datastorage.AccountRepository;
import avans.groep15.themoviedb.datastorage.ListRepository;
import avans.groep15.themoviedb.domain.AddMovie;
import avans.groep15.themoviedb.domain.Languages;
import avans.groep15.themoviedb.domain.WatchList;

public class MovieActivity extends AppCompatActivity implements StatusListener {

    private final ListRepository listRepository = ListRepository.getInstance();
    private final AccountRepository accountRepository = AccountRepository.getInstance();

    private final SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    private final SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    private int movieID;
    private TextView movieTitle;
    private TextView movieDescription;
    private ImageView moviePoster;
    private TextView movieGenre;
    private TextView movieRating;
    private TextView movieAge;
    private TextView movieReleaseDate;
    private TextView movieNativeLanguage;
    private Button addToListButton;
    private Spinner spinner;

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
        addToListButton = findViewById(R.id.addToListButton);
        spinner = findViewById(R.id.spinner);

        // GET INTENT
        Intent intent = getIntent();

        // SET MOVIE DETAILS
        movieID = intent.getIntExtra("movieID", 0);
        movieTitle.setText(intent.getStringExtra("MovieTitle"));
        movieDescription.setText(intent.getStringExtra("MovieDescription"));

        String posterPath = getIntent().getStringExtra("MoviePoster");
        String posterUrl = "https://image.tmdb.org/t/p/original/" + posterPath;
        Glide.with(this)
                .load(posterUrl)
                .error(R.drawable.ic_unknown)
                .into(moviePoster);

        movieGenre.setText(intent.getStringExtra("MovieGenre"));
        movieRating.setText("Rating: " + intent.getStringExtra("MovieRating"));

        movieAge.setText("18+: " + intent.getStringExtra("MovieAge"));

        String releaseDateString = intent.getStringExtra("MovieRelease");
        movieReleaseDate.setText(releaseDateString);


        movieNativeLanguage.setText("Language: " + Languages.getLanguageName(intent.getStringExtra("MovieNativeLanguage")));


        String movieRelease = intent.getStringExtra("MovieRelease");
        if (movieRelease != null) {
            Date date = null;
            try {
                date = inputDateFormat.parse(movieRelease.toString());
            } catch (ParseException e) {
                try {
                    date = outputDateFormat.parse(movieRelease.toString());
                } catch (ParseException ignore) {
                }
            }
            String formattedDate = "Release Date: ";
            if (date != null) {
                formattedDate += outputDateFormat.format(date);
            } else {
                formattedDate += "N/A";
            }
            movieReleaseDate.setText(formattedDate);
        } else {
            movieReleaseDate.setText("Release Date: N/A");
        }

        setWatchLists();
    }

    private void setWatchLists() {
        // Set watch lists if available
        if (accountRepository.getHasLoggedInObservable().getValue() == null ||
           !accountRepository.getHasLoggedInObservable().getValue()) {
            addToListButton.setVisibility(View.GONE);
            Toast.makeText(this, "You need to be logged in to add to a list", Toast.LENGTH_SHORT).show();
            return;
        }
        List<WatchList> watchLists = listRepository.getWatchListObservable().getValue();
        if (watchLists == null || watchLists.size() == 0) {
            addToListButton.setVisibility(View.GONE);
            Toast.makeText(this, "There are no lists to add the movie to", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayAdapter<WatchList> spinnerList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, watchLists);
        spinnerList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerList);
        addToListButton.setVisibility(View.VISIBLE);
    }

    public void addToList(View view) {
        WatchList watchList = (WatchList) spinner.getSelectedItem();
        new AddToListTask(this).execute(new AddMovie(movieID, watchList.getId()));
    }

    @Override
    public void isSuccessful(boolean successful) {
        if (successful) {
            Toast.makeText(this, "Added movie to list", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Movie already added to list", Toast.LENGTH_SHORT).show();
        }
    }
}