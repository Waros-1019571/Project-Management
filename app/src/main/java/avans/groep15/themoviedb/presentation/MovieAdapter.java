package avans.groep15.themoviedb.presentation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.domain.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final static String TAG = MovieAdapter.class.getSimpleName();
    private final Locale locale = new Locale.Builder().setLanguage("nl").setRegion("NL").build();

    private final Context context;
    private List<Movie> movies;
    private ArrayList<Movie> originalMovies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
        originalMovies = new ArrayList<>(movies);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Log.d(TAG, "Creating movie recycler view");
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.movie_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = this.movies.get(position);
        Log.d(TAG, "Binding movie " + movie.getOriginal_title() + " to position " + position);

        holder.TitleTextView.setText(movie.getOriginal_title());

        // Set the genres TextView to display the first 1-3 genres of the movie
        List<String> genres = movie.getGenreString();

        if (genres.size() > 0) {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (String genre : genres) {
                sb.append(genre);
                sb.append(", ");
                count++;
                if (count == 2) {
                    break;
                }
            }
            sb.setLength(sb.length() - 2);
            holder.GenreTextView.setText(sb.toString());
        } else {
            holder.GenreTextView.setText("N/A");
        }


        holder.RatingTextView.setText("" + movie.getVote_average());


        //CHANGE DATE TO SIMPLEDATE FORMAT
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        if (movie.getRelease_date() != null) {
            Date date = null;
            try {
                date = inputDateFormat.parse(movie.getRelease_date().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            String formattedDate = outputDateFormat.format(date);
            holder.DateTextView.setText(formattedDate);
        } else {
            holder.DateTextView.setText("N/A");
        }


        // Set the movie poster image using Glide
        String posterPath = "https://image.tmdb.org/t/p/w500" + this.movies.get(position).getPoster_path();
        Glide.with(holder.imageView)
                .load(posterPath)
                .placeholder(R.drawable.ic_unknown)
                .into(holder.imageView);

        // Set the click listener to open the movie details activity when the user taps on the movie
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = movies.get(position);
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("movieID", movie.getId());
                intent.putExtra("MovieTitle", movie.getOriginal_title());
                intent.putExtra("MovieDescription", ("" + movie.getOverview()));
                intent.putExtra("MoviePoster", ("" + movie.getPoster_path()));

                List<String> list = movie.getGenreString();
                String listString = "";
for (String i : list) {
    listString += i + " ";
}

                intent.putExtra("MovieGenre", (listString));
                intent.putExtra("MovieRating", ("" + movie.getVote_average()));
                intent.putExtra("MovieAge", ("" + movie.isAdult()));
                intent.putExtra("MovieRelease", ("" + movie.getRelease_date()));
                intent.putExtra("MovieNativeLanguage", ("" + movie.getOriginal_language()));
                intent.putExtra("MovieActor", ("" + movie.getActors()));
                context.startActivity(intent);
                Log.i(TAG, ("Clicked on " + movie.getOriginal_title()));
            }
        });

    }


    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        }
        return movies.size();

    }


    public void setMeals(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        private TextView TitleTextView;
        private TextView GenreTextView;
        private TextView DateTextView;
        private TextView RatingTextView;


        public MovieViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.movieImage);
            TitleTextView = view.findViewById(R.id.movieTitle);
            GenreTextView = view.findViewById(R.id.movieGenre);
            DateTextView = view.findViewById(R.id.movieDate);
            RatingTextView = view.findViewById(R.id.movieRating);
        }
    }

    public void filter(String text) {
        if (text.isEmpty()) {
            // If the search text is empty, reset the original list
            movies.clear();
            movies.addAll(originalMovies);
        } else {
            // If the search text is not empty, filter the list based on the text
            text = text.toLowerCase();
            ArrayList<Movie> filteredMovies = new ArrayList<>();
            for (Movie movie : originalMovies) {
                if (movie.getOriginal_title().toLowerCase().contains(text)) {
                    filteredMovies.add(movie);
                }
            }
            movies.clear();
            movies.addAll(filteredMovies);
        }
        notifyDataSetChanged();
    }

}

