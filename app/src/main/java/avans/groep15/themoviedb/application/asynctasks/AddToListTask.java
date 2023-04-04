package avans.groep15.themoviedb.application.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.Observer;

import avans.groep15.themoviedb.application.listeners.StatusListener;
import avans.groep15.themoviedb.datastorage.ListRepository;
import avans.groep15.themoviedb.domain.AddMovie;

public class AddToListTask extends AsyncTask<AddMovie, Void, Void> {
    private final ListRepository repository = ListRepository.getInstance();
    private final static String TAG = AddToListTask.class.getSimpleName();


    public AddToListTask(StatusListener statusListener) {
        this.repository.getSuccessObservable().observe(statusListener, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                statusListener.isSuccessful(success);
            }
        });
    }


    @Override
    protected Void doInBackground(AddMovie... addMovie) {
        Log.d(TAG, "Adding movie to list");
        repository.addToWatchList(addMovie[0]);
        return null;
    }
}
