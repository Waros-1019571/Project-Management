package avans.groep15.themoviedb.application.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.Observer;

import java.util.List;

import avans.groep15.themoviedb.application.listeners.ListListener;
import avans.groep15.themoviedb.datastorage.ListRepository;
import avans.groep15.themoviedb.domain.WatchList;

public class CreateListTask extends AsyncTask<WatchList, Void, Void> {
    private final static String TAG = LoginTask.class.getSimpleName();
    private final ListRepository repository = ListRepository.getInstance();

    public CreateListTask(ListListener listListener) {
        this.repository.getWatchListObservable().observe(listListener, new Observer<List<WatchList>>() {
            @Override
            public void onChanged(List<WatchList> lists) {
                Log.d(TAG, "List added");
                listListener.listAdded(lists);
            }
        });
    }

    @Override
    protected Void doInBackground(WatchList... watchLists) {
        Log.d(TAG, "Adding list");
        repository.postWatchLists(watchLists[0]);
        return null;
    }
}
