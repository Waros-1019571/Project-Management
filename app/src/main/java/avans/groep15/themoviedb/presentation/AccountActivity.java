package avans.groep15.themoviedb.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.application.asynctasks.GetListsTask;
import avans.groep15.themoviedb.application.asynctasks.LogOutTask;
import avans.groep15.themoviedb.application.listeners.ListListener;
import avans.groep15.themoviedb.application.listeners.LogOutListener;
import avans.groep15.themoviedb.datastorage.AccountRepository;
import avans.groep15.themoviedb.datastorage.ListRepository;
import avans.groep15.themoviedb.domain.WatchList;

public class AccountActivity extends AppCompatActivity implements LogOutListener, ListListener {

    private final ListRepository listRepository = ListRepository.getInstance();
    private TextView usernameLoggedIn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged_in_account_activity);

        this.usernameLoggedIn = findViewById(R.id.usernameLoggedIn);
        String username = AccountRepository.getInstance().getAccountObservable().getValue().getUsername();
        this.usernameLoggedIn.setText(username);

        List<WatchList> watchLists = listRepository.getWatchListObservable().getValue();
        if (watchLists == null || watchLists.size() == 0) {
            // Load watch lists on successful login
            new GetListsTask(this).execute();
        }
    }

    public void logOut(View view) {
        new LogOutTask(this).execute();
        Toast.makeText(this, "Logging Out...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hasLoggedOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void receiveLists(List<WatchList> lists) {
        Toast.makeText(this, "Downloaded lists from API", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, "You have no lists yet", Toast.LENGTH_SHORT).show();
    }
}
