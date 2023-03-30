package avans.groep15.themoviedb.presentation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.domain.Movie;
import avans.groep15.themoviedb.domain.PersonalList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private final Context context;
    private List<PersonalList> lists;

    public ListAdapter(Context context, List<PersonalList> lists) {
        this.context = context;
        this.lists = lists;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.movie_item, viewGroup, false);
        return new ListAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        PersonalList list = this.lists.get(position);

        holder.listTitleTextView.setText(list.getTitle());
        holder.listDescriptionTextView.setText(list.getDescription());
        holder.listNumberTextView.setText(list.getItems());
    }

    @Override
    public int getItemCount() {
        return 0;
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
