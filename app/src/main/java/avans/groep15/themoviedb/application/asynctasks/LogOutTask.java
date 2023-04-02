package avans.groep15.themoviedb.application.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.Observer;

import avans.groep15.themoviedb.application.listeners.LogOutListener;
import avans.groep15.themoviedb.datastorage.AccountRepository;

public class LogOutTask extends AsyncTask<Void, Void, Void> {
    private final static String TAG = LoginTask.class.getSimpleName();
    private final AccountRepository repository = AccountRepository.getInstance();

    public LogOutTask(LogOutListener logOutListener) {
        this.repository.getHasLoggedInObservable().observe(logOutListener, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                if (!bool) {
                    Log.d(TAG, "Logged out");
                    logOutListener.hasLoggedOut();
                }
            }
        });
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "Logging out");
        repository.performLogOut();
        return null;
    }
}
