package avans.groep15.themoviedb.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import avans.groep15.themoviedb.R;
import avans.groep15.themoviedb.application.asynctasks.CreateListTask;
import avans.groep15.themoviedb.application.listeners.ListListener;
import avans.groep15.themoviedb.domain.Validation;
import avans.groep15.themoviedb.domain.WatchList;

public class AddListActivity extends AppCompatActivity implements ListListener {

    private TextView titleInput;
    private TextView descriptionInput;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_add_activity);

        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        addButton = findViewById(R.id.addNewListButton);
    }

    public void addList(View view) {
        String title = titleInput.getText().toString();
        if (Validation.isEmptyString(title)) {
            Toast.makeText(this, "Missing title", Toast.LENGTH_SHORT).show();
            return;
        }
        String description = descriptionInput.getText().toString();
        if (Validation.isEmptyString(description)) {
            Toast.makeText(this, "Missing description", Toast.LENGTH_SHORT).show();
            return;
        }
        new CreateListTask(this).execute(new WatchList(title, description));
        addButton.setEnabled(false);
    }

    @Override
    public void listAdded(List<WatchList> lists) {
        addButton.setEnabled(true);
        Toast.makeText(this, "Added list!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
