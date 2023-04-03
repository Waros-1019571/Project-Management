package avans.groep15.themoviedb.presentation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        Log.d(TAG, "Creating meal recycler view");
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.movie_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = this.movies.get(position);
        Log.d(TAG, "Binding meal " + movie.getOriginal_title() + " to position " + position);


        holder.TitleTextView.setText(movie.getOriginal_title());
        //Grabs top 3 genres --> if it doesnt have 3 genres it throws an error
        //         String genres = movie.getGenres().get(0) + " " + movie.getGenres().get(1) + " " + movie.getGenres().get(2);
        //         holder.GenreTextView.setText(genres);
        holder.RatingTextView.setText("" + movie.getVote_average());
        holder.DateTextView.setText("" + movie.getRelease_date());

        //SET IMAGE
        String posterPath = "https://image.tmdb.org/t/p/w500" + this.movies.get(position).getPoster_path();
        Glide.with(holder.imageView)
                .load(posterPath)
                .placeholder(R.drawable.ic_unknown)
                .into(holder.imageView);
//OnClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = movies.get(position);
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("movieID", movie.getId());
                intent.putExtra("MovieTitle", movie.getOriginal_title());
                intent.putExtra("MovieDescription", ("" + movie.getOverview()));
                intent.putExtra("MoviePoster", ("" + movie.getPoster_path()));
                intent.putExtra("MovieGenre", ("" + movie.getGenres()));
                intent.putExtra("MovieRating", ("" + movie.getVote_average()));
                intent.putExtra("MovieAge", ("" + movie.getAdult()));
                intent.putExtra("MovieRelease",("" + movie.getRelease_date()));
                intent.putExtra("MovieNativeLanguage", ("" + movie.getOriginal_language()));
                intent.putExtra("MovieActor", ("" + movie.getActors()));
                context.startActivity(intent);
                Log.i(TAG, ("Clicked on " + movie.getOriginal_title()));
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.movies.size();
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

}

