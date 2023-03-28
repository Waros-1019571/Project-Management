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
import java.util.Date;
import java.util.List;
import java.util.Locale;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.domain.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final static String TAG = MovieAdapter.class.getSimpleName();
    private final Locale locale = new Locale.Builder().setLanguage("nl").setRegion("NL").build();

    private final Context context;
    private final List<Movie> movies;

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

        Log.d(TAG, "Binding meal " + movie.getTitle() + " to position " + position);

        holder.TitleTextView.setText(movie.getTitle());
        //Grabs top 3 genres --> if it doesnt have 3 genres it throws an error
        String genres = movie.getGenres().get(0) + " " + movie.getGenres().get(1) + " " + movie.getGenres().get(2);
        holder.GenreTextView.setText(genres);
        holder.RatingTextView.setText("" + movie.getRating());
        holder.DateTextView.setText(movie.getReleaseDate());

        Glide.with(holder.imageView)
                .load(this.movies.get(position).getImageUrl())
                .placeholder(R.drawable.ic_unknown)
                .into(holder.imageView);

//OnClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = movies.get(position);
//                Intent intent = new Intent(context, .class);
//                intent.putExtra("movie", movie);
//                context.startActivity(intent);
                Log.i(TAG, ("Clicked on " + movie.getTitle()));
            }
        });
    }


    @Override
    public int getItemCount() {
        return movies.size();
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

