package avans.groep15.themoviedb.application.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.Observer;

import avans.groep15.themoviedb.application.listeners.LoginListener;
import avans.groep15.themoviedb.datastorage.LoginRepository;
import avans.groep15.themoviedb.domain.LoginData;

public class LoginTask extends AsyncTask<LoginData, Void, Void>  {
    private final static String TAG = LoginTask.class.getSimpleName();
    private final LoginRepository repository = LoginRepository.getInstance();

    public LoginTask(LoginListener loginListener) {
        this.repository.getTokenObservable().observe(loginListener, new Observer<String>() {
            @Override
            public void onChanged(String token) {
                if (token == null) {
                    Log.d(TAG, "Parsing login failure to login listener");
                    loginListener.hasFailed();
                } else {
                    Log.d(TAG, "Parsing successful login to login listener");
                    loginListener.hasLoggedIn(token);
                }
            }
        });
    }

    @Override
    protected Void doInBackground(LoginData... loginDatas) {
        Log.d(TAG, "Logging in");
        LoginData loginData = loginDatas[0];
        repository.performLogin(loginData);
        return null;
    }


}
