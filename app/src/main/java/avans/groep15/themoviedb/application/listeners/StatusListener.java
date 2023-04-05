package avans.groep15.themoviedb.application.listeners;

import androidx.lifecycle.LifecycleOwner;

public interface StatusListener extends LifecycleOwner {
    void isSuccessful(boolean successful);
}
