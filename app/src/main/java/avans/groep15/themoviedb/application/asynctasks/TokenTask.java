package avans.groep15.themoviedb.application.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.Observer;

import avans.groep15.themoviedb.application.listeners.TokenListener;
import avans.groep15.themoviedb.datastorage.AccountRepository;

public class TokenTask extends AsyncTask<Void, Void, Void> {
    private final static String TAG = LoginTask.class.getSimpleName();
    private final AccountRepository repository = AccountRepository.getInstance();

    public TokenTask (TokenListener tokenListener) {
        this.repository.getTokenObservable().observe(tokenListener, new Observer<String>() {
            @Override
            public void onChanged(String token) {
                if (token == null) {
                    Log.d(TAG, "Failed to get token");
                } else {
                    Log.d(TAG, "Parsing token to login listener");
                    tokenListener.receiveToken(token);
                }
            }
        });
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "Retrieving token");
        repository.getToken();
        return null;
    }
}
