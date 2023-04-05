package avans.groep15.themoviedb.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import avans.groep15.themoviedb.R;

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
                .error(R.drawable.ic_unknown)
                .into(moviePoster);


        movieGenre.setText(intent.getStringExtra("MovieGenre"));


        movieRating.setText("Rating: " + intent.getStringExtra("MovieRating"));

        movieAge.setText("18+: " + intent.getStringExtra("MovieAge"));

        String releaseDateString = intent.getStringExtra("MovieRelease");
        movieReleaseDate.setText(releaseDateString);


        movieNativeLanguage.setText("Language: " + getLanguageName(intent.getStringExtra("MovieNativeLanguage")));


    }

    public static String getLanguageName(String languageCode) {
        switch (languageCode) {
            case "af":
                return "Afrikaans";
            case "am":
                return "Amharic";
            case "ar":
                return "Arabic";
            case "az":
                return "Azerbaijani";
            case "bg":
                return "Bulgarian";
            case "bn":
                return "Bengali";
            case "bs":
                return "Bosnian";
            case "ca":
                return "Catalan";
            case "cs":
                return "Czech";
            case "cy":
                return "Welsh";
            case "da":
                return "Danish";
            case "de":
                return "German";
            case "el":
                return "Greek";
            case "en":
                return "English";
            case "eo":
                return "Esperanto";
            case "es":
                return "Spanish";
            case "et":
                return "Estonian";
            case "eu":
                return "Basque";
            case "fa":
                return "Persian";
            case "fi":
                return "Finnish";
            case "fr":
                return "French";
            case "ga":
                return "Irish";
            case "gl":
                return "Galician";
            case "gu":
                return "Gujarati";
            case "he":
                return "Hebrew";
            case "hi":
                return "Hindi";
            case "hr":
                return "Croatian";
            case "ht":
                return "Haitian";
            case "hu":
                return "Hungarian";
            case "hy":
                return "Armenian";
            case "id":
                return "Indonesian";
            case "is":
                return "Icelandic";
            case "it":
                return "Italian";
            case "ja":
                return "Japanese";
            case "jv":
                return "Javanese";
            case "ka":
                return "Georgian";
            case "kk":
                return "Kazakh";
            case "km":
                return "Khmer";
            case "kn":
                return "Kannada";
            case "ko":
                return "Korean";
            case "ku":
                return "Kurdish";
            case "ky":
                return "Kyrgyz";
            case "la":
                return "Latin";
            case "lb":
                return "Luxembourgish";
            case "lo":
                return "Lao";
            case "lt":
                return "Lithuanian";
            case "lv":
                return "Latvian";
            case "mk":
                return "Macedonian";
            case "ml":
                return "Malayalam";
            case "mn":
                return "Mongolian";
            case "mr":
                return "Marathi";
            case "ms":
                return "Malay";
            case "mt":
                return "Maltese";
            case "my":
                return "Burmese";
            case "ne":
                return "Nepali";
            case "nl":
                return "Dutch";
            case "no":
                return "Norwegian";
            case "pa":
                return "Punjabi";
            case "pl":
                return "Polish";
            case "ps":
                return "Pashto";
            case "pt":
                return "Portuguese";
            case "ro":
                return "Romanian";
            case "ru":
                return "Russian";
            case "tg":
                return "Tajik";
            case "tr":
                return "Turkish";
            case "sv":
                return "Swedish";
            case "th":
                return "Thai";
            case "uk":
                return "Ukrainian";
            case "sr":
                return "Serbian";
            case "ta":
                return "Tamil";
            case "te":
                return "Telugu";
            case "ur":
                return "Urdu";
            case "sk":
                return "Slovak";
            case "sl":
                return "Slovenian";
            case "sw":
                return "Swahili";
            case "zu":
                return "Zulu";
            case "xh":
                return "Xhosa";
            case "so":
                return "Somali";
            default:
                return languageCode;
        }
    }
}