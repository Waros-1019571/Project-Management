package avans.groep15.themoviedb.application.listeners;

import androidx.lifecycle.LifecycleOwner;

public interface LoginListener extends LifecycleOwner {
    void hasLoggedIn(boolean loggedIn);
}
