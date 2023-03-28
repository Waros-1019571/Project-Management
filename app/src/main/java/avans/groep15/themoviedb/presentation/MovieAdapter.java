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
        View view = inflater.inflate(R.layout.meal_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
//        Movie movie = this.movies.get(position);
//
//        Log.d(TAG, "Binding meal " + movie.getTitle() + " to position " + position);
//
//        holder.name.setText(meal.getName());
//        holder.price.setText(getPrice(meal.getPrice()));
//        holder.date.setText(getDate(meal.getDateTime()));
//        Glide.with(this.context).load(this.meals.get(position).getImageUrl()).fallback(R.drawable.ic_unknown).error(R.drawable.ic_unknown).into(holder.image);
//
//        holder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Movie movie = movies.get(position);
//                Intent intent = new Intent(context, MovieDetails.class);
//                intent.putExtra("meal", meal);
//                context.startActivity(intent);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return this.movies.size();
    }









    class MovieViewHolder extends RecyclerView.ViewHolder {



        public MovieViewHolder(View view) {
            super(view);

        }
    }

}

