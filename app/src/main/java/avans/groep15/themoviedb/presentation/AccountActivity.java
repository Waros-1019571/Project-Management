package avans.groep15.themoviedb.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.application.asynctasks.GetMovieTask;
import avans.groep15.themoviedb.application.listeners.LoginListener;

public class AccountActivity extends AppCompatActivity implements LoginListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    private TextView emailAddressInput;
    private TextView passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_logged_in_account_activity);
    }

    @Override
    public void hasLoggedIn(String token) {

    }

    @Override
    public void hasFailed() {

    }

    public void login(View view) {

    }
}
