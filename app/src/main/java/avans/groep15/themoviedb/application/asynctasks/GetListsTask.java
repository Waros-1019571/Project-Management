package avans.groep15.themoviedb.application.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.Observer;

import java.util.List;

import avans.groep15.themoviedb.application.listeners.ListListener;
import avans.groep15.themoviedb.datastorage.ListRepository;
import avans.groep15.themoviedb.domain.WatchList;

public class GetListsTask extends AsyncTask<Void, Void, Void> {
    private final static String TAG = GetListsTask.class.getSimpleName();
    private final ListRepository repository = ListRepository.getInstance();

    public GetListsTask(ListListener listListener) {
        this.repository.getWatchListObservable().observe(listListener, new Observer<List<WatchList>>() {
            @Override
            public void onChanged(List<WatchList> success) {

                listListener.receiveLists(success);
            }
        });
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "Getting lists");
        repository.getWatchLists();
        return null;
    }
}
