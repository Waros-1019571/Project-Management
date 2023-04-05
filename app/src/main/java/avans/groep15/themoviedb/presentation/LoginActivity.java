package avans.groep15.themoviedb.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.application.asynctasks.LoginTask;
import avans.groep15.themoviedb.application.asynctasks.TokenTask;
import avans.groep15.themoviedb.application.listeners.LoginListener;
import avans.groep15.themoviedb.application.listeners.TokenListener;
import avans.groep15.themoviedb.datastorage.AccountRepository;
import avans.groep15.themoviedb.domain.LoginData;

public class LoginActivity extends AppCompatActivity implements TokenListener, LoginListener {

    private final static String TAG = MainActivity.class.getSimpleName();
    private final AccountRepository accountRepository = AccountRepository.getInstance();
    private String token = "";

    private EditText userNameInput;
    private EditText passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_logged_in_account_activity);
        if (Boolean.TRUE.equals(accountRepository.getHasLoggedInObservable().getValue())) {
            // Already logged in
            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
            return;
        }
        new TokenTask(this).execute();

        this.userNameInput = findViewById(R.id.usernameInput);
        this.passwordInput = findViewById(R.id.passwordInput);
        this.loginButton = findViewById(R.id.loginButton);
    }

    @Override
    public void receiveToken(String token) {
        this.token = token;
    }

    @Override
    public void hasLoggedIn(boolean loggedIn) {
        this.loginButton.setEnabled(true);
        if (!loggedIn) {
            Toast.makeText(this, "Username or password are not correct!", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(TAG, "" + loggedIn);
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Log.d(TAG, "Login button pressed");
        String userName = this.userNameInput.getText().toString();
        String password = this.passwordInput.getText().toString();
        new LoginTask(this).execute(new LoginData(userName, password, this.token));
        this.loginButton.setEnabled(false);
        Toast.makeText(this, "Logging In...", Toast.LENGTH_SHORT).show();

    }
}
