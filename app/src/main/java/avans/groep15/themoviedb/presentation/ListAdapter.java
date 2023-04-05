package avans.groep15.themoviedb.presentation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.domain.Movie;
import avans.groep15.themoviedb.domain.WatchList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private final Context context;
    private List<WatchList> lists;

    public ListAdapter(Context context, List<WatchList> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ListAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        WatchList list = this.lists.get(position);

        holder.listTitleTextView.setText(list.getName());
        holder.listDescriptionTextView.setText(list.getDescription());
        holder.listNumberTextView.setText("" + list.getItems());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WatchList watchList = lists.get(position);
                Intent intent = new Intent(context, WatchListActivity.class);


                //TEMPLIST
                ArrayList<Movie> tempList = new ArrayList<>();
                tempList.addAll(watchList.getMovies());

                //CHECK WHATS IN TEMPLIST --> IT GETS MOVIES FROM LISTACTIVITY
                System.out.println("WatchList OnClick: " + watchList.getMovies());


                //PARTS TO GIVE WITH INTENT
                intent.putExtra("listId", watchList.getId());
                intent.putExtra("listTitle", watchList.getName());
                intent.putExtra("listDescription", watchList.getDescription());
                intent.putExtra("movieList", tempList);

                //START ACTIVITY WITH INTENT
                context.startActivity(intent);
                Log.i("TAG", ("Clicked on " + list.getName()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView listTitleTextView;
        private TextView listDescriptionTextView;
        private TextView listNumberTextView;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            listTitleTextView = itemView.findViewById(R.id.listTitle);
            listDescriptionTextView = itemView.findViewById(R.id.listDescription);
            listNumberTextView = itemView.findViewById(R.id.listNumberOfItems);
        }
    }

}
